package ma.digency.gov.amc.service.library;

import liquibase.datatype.LiquibaseDataType;
import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.dto.searchParticipant.AuthorSearchCriteria;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.AuthorRepository;

import ma.digency.gov.amc.repository.entity.author.Author;
import ma.digency.gov.amc.repository.entity.author.AuthorCriteriaRepository;
import ma.digency.gov.amc.repository.entity.search.AuthorPage;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorCriteriaRepository authorCriteriaRepository;
    private final ReferenceSequenceService referenceSequenceService;

    @Override
    public Author findAuthorByRefAuthor(String refAuthor) {
        return authorRepository.findAuthorByRefAuthor(refAuthor)
                .orElseThrow(() -> new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_EXIHIBITOR_NOT_FOUND));
    }

    @Override
    public Author createOrUpdateAuthor(Author author ) {
        if (null == author.getRefAuthor()) {
            var ref = referenceSequenceService.generateRefAuthor();
            author.setRefAuthor(ref);
        }
        return authorRepository.save(author);
    }

    @Override
    public Author createOrUpdateAuthorAndImage(Author author, MultipartFile multipartFile) {
        try {
            if (null == author.getRefAuthor()) {
            var ref = referenceSequenceService.generateRefAuthor();

            author.setRefAuthor(ref);
        }
           if(multipartFile != null){ author.setPicture(multipartFile.getBytes());}
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_TECHNICAL_ERROR);
        }

        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(String refAuthor) {
        Author author = findAuthorByRefAuthor(refAuthor);
        authorRepository.delete(author);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }
    
    @Override
    public Page<Author> findAuthorsPageable(Pageable pageable) {
       return authorRepository.findAll(pageable);
    }

    @Override
    public Page<Author> getAuthor(AuthorPage authorPage, AuthorSearchCriteria authorSearchCriteria) {
        return authorCriteriaRepository.findAllWithFilters(authorPage,authorSearchCriteria);
    }
}
