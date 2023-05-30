package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.participant.PrinterRequest;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.repository.entity.printer.Printer;


import java.util.List;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface PrinterMapper {

    PrinterResponse printerToPrinterResponse(Printer printer);

    Printer  printerResponseToPrinter (PrinterResponse response);

    Printer printerRequestToPrinter(PrinterRequest printerRequest);

    Printer updatePrinterFromPrinterResponse(PrinterResponse response, Printer printer);

    List<PrinterResponse> printerToPrinterResponse(List<Printer> printer);



}
