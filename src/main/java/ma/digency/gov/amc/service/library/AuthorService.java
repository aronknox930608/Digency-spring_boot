package ma.digency.gov.amc.service.library;


import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.search.AuthorPage;
import ma.digency.gov.amc.repository.entity.siel.Edition;
import ma.digency.gov.amc.repository.entity.siel.Exhibitor;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface AuthorService {

    Author findAuthorByRefAuthor(String refAuthor);

    Author createOrUpdateAuthor(Author author);

    Author createOrUpdateAuthorAndImage(Author author, MultipartFile multipartFile);

    void deleteAuthor(String refAuthor);

    List<Author> findAllAuthors();

    Page<Author> findAuthorsPageable(Pageable pageable);

    Page<Author> getAuthor(AuthorPage authorPage, AuthorSearchCriteria authorSearchCriteria);
}
