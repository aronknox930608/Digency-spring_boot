package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.library.LibraryResponse;
import ma.digency.gov.amc.dto.participant.PrinterResponse;
import ma.digency.gov.amc.repository.entity.printer.Printer;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelPrinterService {

    List<PrinterResponse> getAllPrinters();

    ByteArrayInputStream exportPrinterData(List<PrinterResponse> printer);


}
