package ma.digency.gov.amc.service.proposalproject;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.BookPersonAccountRepository;
import ma.digency.gov.amc.repository.entity.proposalproject.BookPersonAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BookPersonAccountServiceImpl implements BookPersonAccountService {

    private final BookPersonAccountRepository bookPersonRepository;

    @Override
    public BookPersonAccount findByRefBookPerson(String refBookPerson) {
        return bookPersonRepository.findByRefBookPerson(refBookPerson).orElseThrow(()->
                new MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_BOOK_PERSON_FOUND));
    }

    @Override
    public Page<BookPersonAccount> findAllBookPerson(Pageable pageable) {
        return bookPersonRepository.findAll(pageable);
    }

    @Override
    public BookPersonAccount saveOrUpdate(BookPersonAccount bookPerson) {
        return bookPersonRepository.save(bookPerson);
    }
}
