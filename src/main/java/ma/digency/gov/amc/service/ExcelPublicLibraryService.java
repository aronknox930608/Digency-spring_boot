package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.library.LibraryResponse;
import ma.digency.gov.amc.dto.publiclibrary.PublicLibraryResponse;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelPublicLibraryService {

    List<PublicLibraryResponse> getAllPublicLibraries();

    ByteArrayInputStream exportPublicLibraryData(List<PublicLibraryResponse> library);


}
