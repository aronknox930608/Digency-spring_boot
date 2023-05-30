package ma.digency.gov.amc.service.participant;


import ma.digency.gov.amc.dto.searchParticipant.LibrarySearchCriteria;
import ma.digency.gov.amc.dto.searchParticipant.PrinterSearchCriteria;
import ma.digency.gov.amc.repository.entity.library.Library;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.search.LibraryPage;
import ma.digency.gov.amc.repository.entity.search.PrinterPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PrinterService {

    Printer findPrinterByRefPrinter(String refPrinter);

    Printer createOrUpdatePrinter(Printer printer);

    void deletePrinter(String refPrinter);

    List<Printer> findAllPrinters();

    Page<Printer> findPrintersPageable(Pageable pageable);

    Page<Printer> getPrinters(PrinterPage printerPage, PrinterSearchCriteria printerSearchCriteria);
}
