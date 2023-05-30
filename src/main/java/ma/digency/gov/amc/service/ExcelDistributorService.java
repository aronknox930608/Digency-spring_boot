package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.participant.DistributorResponse;
import ma.digency.gov.amc.dto.participant.PrinterResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelDistributorService {

    List<DistributorResponse> getAllDistributors();

    ByteArrayInputStream exportDistributorsData(List<DistributorResponse> printer);


}
