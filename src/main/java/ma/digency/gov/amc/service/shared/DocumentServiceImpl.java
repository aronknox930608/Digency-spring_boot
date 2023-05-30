package ma.digency.gov.amc.service.shared;

import java.io.*;
import java.util.List;

import ma.digency.gov.amc.service.siel.SielDocumentResource;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.DocumentRepository;
import ma.digency.gov.amc.repository.entity.Document;
import ma.digency.gov.amc.service.siel.SielParticipationDocument;


import java.io.InputStream;



@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public Document uploadDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public Document findByRefDocument(String refDocument) {
        return documentRepository.findByRefDocument(refDocument)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_DOCUMENT_NOT_FOUND));
    }

    @Override
    public List<Document> findByRefObject(String refObject) {
        return documentRepository.findByRefObject(refObject);
    }

    @Override
    public void deleteDocument(Document document) {
        this.documentRepository.delete(document);
    }

    @Override
    public List<Document> findByRefObjectAndRefParent(String refObject, String refParent) {
        return documentRepository.findByRefObjectAndRefParent(refObject,refParent);
    }

    @Override
    public void deleteDocument(String refObject) {
        List<Document> documents=documentRepository.findByRefObject(refObject);
        for (Document document:documents) {
            documentRepository.delete(document);
        }
    }

    @Override
    public byte[] generateDocument(SielParticipationDocument documentRequest, boolean isPurchase) throws IOException {
        String documentClassPath = documentRequest.getResource().getClassPath();
        if (isPurchase) {
            documentClassPath = SielDocumentResource.SIEL_PURCHASE_ORDER_FR_DOC.getClassPath();
        }
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(documentClassPath);
        try (XWPFDocument doc = new XWPFDocument(inputStream)) {

            List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
            //Iterate over paragraph list and check for the replaceable text in each paragraph
            for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
                for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
                    String docText = xwpfRun.getText(0);
                    if (docText != null) {
                        //replacement and setting position
                        docText = docText.replace("#startDateEdition0", documentRequest.getStartDateEdition());
                        docText = docText.replace("#endDateEdtion0", documentRequest.getEndDateEdition());
                        docText = docText.replace("#endDate0", documentRequest.getEndDate());
                        docText = docText.replace("#endDate1", documentRequest.getEndDateFr());
                        docText = docText.replace("#name0", documentRequest.getEstablishmentName());
                        docText = docText.replace("#type0", "");
                        docText = docText.replace("#adress0", documentRequest.getAddress());
                        docText = docText.replace("#responsible0", documentRequest.getResponsible());
                        docText = docText.replace("#phoneNumber0", documentRequest.getPhoneNumber());
                        docText = docText.replace("#fax0", documentRequest.getFaxNumber());
                        docText = docText.replace("#email0", documentRequest.getEmail());
                        docText = docText.replace("#representing0", documentRequest.getRepresenting());
                        docText = docText.replace("#productsExhibited0", documentRequest.getProductsExhibited());
                        docText = docText.replace("#date0", documentRequest.getDate());
                        docText = docText.replace("#rib0", documentRequest.getRib()+"");
                        docText = docText.replace("#cheque0", documentRequest.getCheque());
                        docText = docText.replace("#virement0", documentRequest.getVirement());
                        docText = docText.replace("#pays0", documentRequest.getPays());
                        docText = docText.replace("#totalStand0", documentRequest.getTotalStand());
                        docText = docText.replace("#totalAr0", documentRequest.getTotalLetterAr());
                        docText = docText.replace("#totalFr0", documentRequest.getTotalLetterFr());


                        xwpfRun.setText(docText, 0);
                    }
                }
            }
            for (XWPFTable tbl : doc.getTables()) {
                for (XWPFTableRow row : tbl.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph p : cell.getParagraphs()) {
                            for (XWPFRun r : p.getRuns()) {
                                String text = r.getText(0);
                                if (text != null) {
                                    text = text.replace("#standSize0", documentRequest.getStandSize());
                                    text = text.replace("#amenage0", documentRequest.getStandEquipped());
                                    text = text.replace("#empty0", documentRequest.getStandEmpty());
                                    text = text.replace("#totalStand0", documentRequest.getTotalStand());
                                    text = text.replace("#rib0", documentRequest.getRib()+"");


                                    r.setText(text, 0);
                                }
                            }
                        }
                    }
                }
            }
            // ImportArtistAccountData the docs
            try {
                System.setProperty("javax.xml.transform.TransformerFactory","com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
                ByteArrayOutputStream output = new ByteArrayOutputStream(1024);
                doc.write(output);
                return output.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    @Override
    public void deleteByRefObject(String refObject) {

        List<Document> documents_Demand=findByRefObject(refObject);
        for(Document document:documents_Demand)
        {
            documentRepository.delete(document);
        }

    }

}
