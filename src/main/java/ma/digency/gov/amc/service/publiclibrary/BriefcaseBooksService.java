package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Personnel;

import java.util.List;


public interface BriefcaseBooksService {

    BriefcaseBooks findBriefcaseBooksByRefBriefcaseBooks(String refBriefcaseBooks);

    BriefcaseBooks createOrUpdateBriefcaseBooks(BriefcaseBooks briefcaseBooks);

    void deleteBriefcaseBooks(String refBriefcaseBooks);

    List<BriefcaseBooks> findAllBriefcaseBooks();


}
