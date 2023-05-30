package ma.digency.gov.amc.repository;


import ma.digency.gov.amc.repository.entity.printer.Printer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrinterRepository extends GenericRepository<Printer, Long> {

    Optional<Printer> findPrinterByRefPrinter(String refPrinter);

}
