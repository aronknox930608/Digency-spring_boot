package ma.digency.gov.amc.service.proposalproject;

import ma.digency.gov.amc.repository.entity.proposalproject.BookPersonAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookPersonAccountService {

    BookPersonAccount findByRefBookPerson(String refBookPerson);

    Page<BookPersonAccount> findAllBookPerson(Pageable pageable);

    BookPersonAccount saveOrUpdate(BookPersonAccount bookPerson);


}
