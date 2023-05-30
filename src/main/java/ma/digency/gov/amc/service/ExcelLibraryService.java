package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.library.AuthorResponse;
import ma.digency.gov.amc.dto.library.LibraryResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelLibraryService {

    List<LibraryResponse> getAllLibraries();

    ByteArrayInputStream exportLibraryData(List<LibraryResponse> library);


}
