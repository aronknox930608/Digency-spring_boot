package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.BookPriceRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.*;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;
import ma.digency.gov.amc.service.proposalproject.ArtistAccountService;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.apache.commons.collections4.IterableUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookPriceServiceImpl implements BookPriceService {

    private final BookPriceRepository bookRepository;

    private final ReferenceSequenceService referenceSequenceService;

    private final ArtistAccountService artistAccountService;

    private final DomainService domainService;

    @Override
    public BookPrice createOrUpdateBook(BookPrice book) {
        if(null==book.getRefBook()){
            var ref=referenceSequenceService.generateRefBookPrice();
            book.setRefBook(ref);
        }
        return bookRepository.save(book);
    }

    @Override
    public List<BookPrice> findBookByAuthor(String refAuthor) {
        return bookRepository.findBookByAuthor(refAuthor);
    }

    @Override
    public BookPrice findBookByDemand(String refDemand) {
        return null;
    }

    @Override
    public void delete(BookPrice book) {
        bookRepository.delete(book);
    }

    @Override
    public BookPrice findBookByRef(String refBook) {
        return bookRepository.findBookByRefBook(refBook);
    }

    @Override
    public void deleteBook(String refBook) {
        bookRepository.delete(bookRepository.findBookByRefBook(refBook));
    }

    @Override
    public Void save(MultipartFile file) {
        try{
            List<BookPrice> books=importExcelData(file.getInputStream());
            for(BookPrice book:books){
                book.setRefBook(referenceSequenceService.generateRefBookPrice());
                bookRepository.save(book);
            }}
        catch(IOException e){
            throw  new RuntimeException("fail to store excel data:" + e.getMessage());
        }

        return null;
    }

    @Override
    public List<BookPrice> importExcelData(InputStream is) throws IOException {
        List<BookPrice> books=new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/dd/yy");
        for (Row row : sheet){
            if(IterableUtils.size(row)<=1){
                break;
            }
            if (row.getRowNum() != 0) {
                BookPrice book = new BookPrice();
                book.setTitle(getCellValue(row,0));
                book.setPublishingHouse(getCellValue(row,1));
                book.setPublishingDate(LocalDate.parse(getCellValue(row,2),formatter));
                book.setPagesNumber(Integer.parseInt(getCellValue(row,3)));
                book.setLanguage(getCellValue(row,4));
                book.setAbstractBook(getCellValue(row,5));
                book.setPublicationPlace(getCellValue(row,6));
                book.setCountry(getCellValue(row,7));
                //book.setDomain(domainService.findDomainByName(getCellValue(row,8)));
                book.setAuthor(artistAccountService.findArtistByCin(getCellValue(row,9)).getRefArtistAccount());
                if(book.getAuthor()!=null){
                    books.add(book);
                }

            }
        }
        return books;
    }

    @Override
    public String getCellValue(Row row, int cellNo) {
        DataFormatter formatter=new DataFormatter();
        Cell cell= (Cell) row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    @Override
    public List<BookPrice> getAllBookPrice() {
        return bookRepository.findAll();
    }

    @Override
    public ByteArrayInputStream exportBookPriceData(List<BookPrice> books) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()){
            Sheet sheet=workbook.createSheet("demands");
            Row row = sheet.createRow(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setFillForegroundColor(IndexedColors.AQUA.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            String[] Header={"refBookPrice","PublishingHouse", "PublishingDate","PagesNumber","Language","AbstractBook","PublicationPlace","Country","Domain","writerFirstName","writerLastName","writerCin"};
            for(int i=0;i<Header.length;i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(Header[i]);
                cell.setCellStyle(cellStyle);
            }
            for (int i = 0; i < books.size(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(books.get(i).getRefBook());
                dataRow.createCell(1).setCellValue(books.get(i).getPublishingHouse());
                dataRow.createCell(2).setCellValue(books.get(i).getPublishingDate());
                dataRow.createCell(3).setCellValue(books.get(i).getPagesNumber());
                dataRow.createCell(4).setCellValue(books.get(i).getLanguage());
                dataRow.createCell(5).setCellValue(books.get(i).getAbstractBook());
                dataRow.createCell(6).setCellValue(books.get(i).getPublicationPlace());
                dataRow.createCell(7).setCellValue(books.get(i).getCountry());
                dataRow.createCell(8).setCellValue(books.get(i).getDomain());
                ArtistAccount writer=artistAccountService.findArtistAccountByRef(books.get(i).getAuthor()).get();
                dataRow.createCell(9).setCellValue(writer.getFirstName());
                dataRow.createCell(10).setCellValue(writer.getLastName());
                dataRow.createCell(11).setCellValue(writer.getCin());

            }
            for(int i=0;i<31;i++){
                sheet.autoSizeColumn(i);
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        }catch (IOException ex){
            return null;
        }
    }

}
