package ma.digency.gov.amc.service;

import ma.digency.gov.amc.dto.library.AuthorResponse;

import ma.digency.gov.amc.dto.library.LibraryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;

import org.apache.poi.ss.usermodel.Row;

import java.io.IOException;

import java.io.InputStream;

import java.util.List;

public interface ExcelParticipantService {

    List<AuthorResponse> getAllAuthors();

    ByteArrayInputStream exportAuthorData(List<AuthorResponse> author);


}
