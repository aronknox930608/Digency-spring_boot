package ma.digency.gov.amc.service.participant;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.searchParticipant.PrinterSearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.PrinterRepository;
import ma.digency.gov.amc.repository.entity.printer.Printer;
import ma.digency.gov.amc.repository.entity.printer.PrinterCriteriaRepository;
import ma.digency.gov.amc.repository.entity.search.PrinterPage;
import ma.digency.gov.amc.service.participant.PrinterService;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrinterServiceImpl implements PrinterService {
    private final PrinterRepository printerRepository;
    private final ReferenceSequenceService referenceSequenceService;
    private final PrinterCriteriaRepository printerCriteriaRepository;

    @Override
    public Printer findPrinterByRefPrinter(String refPrinter) {
        return printerRepository.findPrinterByRefPrinter(refPrinter)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Printer createOrUpdatePrinter(Printer printer) {
        if (null == printer.getRefPrinter()) {
            var ref = referenceSequenceService.generateRefPrinter();
            printer.setRefPrinter(ref);
        }
        return printerRepository.save(printer);
    }

    @Override
    public void deletePrinter(String refPrinter) {
        Printer printer = findPrinterByRefPrinter(refPrinter);
        printerRepository.delete(printer);
    }

    @Override
    public List<Printer> findAllPrinters() {
       return printerRepository.findAll();
    }

    @Override
    public Page<Printer> findPrintersPageable(Pageable pageable) {
        return printerRepository.findAll(pageable);
    }

    @Override
    public Page<Printer> getPrinters(PrinterPage printerPage, PrinterSearchCriteria printerSearchCriteria) {
        return printerCriteriaRepository.findAllWithFilters(printerPage,printerSearchCriteria);
    }
}
