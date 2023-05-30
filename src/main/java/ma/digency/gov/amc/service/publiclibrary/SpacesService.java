package ma.digency.gov.amc.service.publiclibrary;


import ma.digency.gov.amc.repository.entity.publiclibrary.BriefcaseBooks;
import ma.digency.gov.amc.repository.entity.publiclibrary.Spaces;

import java.util.List;


public interface SpacesService {

    Spaces findSpacesByRefSpaces(String refSpaces);

    Spaces createOrUpdateSpaces(Spaces spaces);

    void deleteSpaces(String refSpaces);

    List<Spaces> findAllSpaces();


}
