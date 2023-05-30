package ma.digency.gov.amc.service.attributionsprix;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AwardBookRepository;
import ma.digency.gov.amc.repository.entity.attributionsprix.AwardBook;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardBookServiceImpl implements  AwardBookService{

    private final AwardBookRepository awardBookRepository;

    private  final ReferenceSequenceService referenceSequenceService;

    @Override
    public AwardBook findAwardBookByRefAwardBook(String refAwardBook) {
        return awardBookRepository.findAwardBookByRefAwardBook(refAwardBook);
    }

    @Override
    public AwardBook updateAwardBook(AwardBook awardBook) {
        return awardBookRepository.save(awardBook);
    }

    @Override
    public AwardBook createOrUpdate(AwardBook awardBook) {
        if(null==awardBook.getRefAwardBook()){
            var ref=referenceSequenceService.generateRefAwardBook();
            awardBook.setRefAwardBook(ref);
        }
        return awardBookRepository.save(awardBook);
    }

    @Override
    public List<AwardBook> getAllAwards() {
        return awardBookRepository.findAll();
    }

    @Override
    public void deleteAwardBook(AwardBook awardBook) {
        awardBookRepository.delete(awardBook);
    }
}
