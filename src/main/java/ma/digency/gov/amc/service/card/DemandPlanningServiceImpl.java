package ma.digency.gov.amc.service.card;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.exception.MinistryOfCultureBusinessException;
import ma.digency.gov.amc.exception.MinistryOfCultureMessage;
import ma.digency.gov.amc.repository.DemandPlanningCriteriaRepository;
import ma.digency.gov.amc.repository.DemandPlanningRepository;
import ma.digency.gov.amc.repository.entity.artistCard.DemandPlanning;
import ma.digency.gov.amc.utils.enumeration.StatusEnum;
import ma.digency.gov.amc.utils.searching.DemandPlanningCardCriteria;
import ma.digency.gov.amc.utils.service.ReferenceSequenceService;
import ma.digency.gov.amc.utils.DemandPlanningPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DemandPlanningServiceImpl implements DemandPlanningService {

    private final ReferenceSequenceService referenceSequenceService;
    private final DemandPlanningRepository demandPlanningRepository;
    private final DemandPlanningCriteriaRepository demandPlanningCriteriaRepository;

    @Override
    public DemandPlanning createOrUpdateDemandPlanning(DemandPlanning demandPlanning) {
        if (null == demandPlanning.getRefDemandPlanning()) {
            var ref = referenceSequenceService.generateRefDemandPlanning();
            demandPlanning.setRefDemandPlanning(ref);
        }
        return demandPlanningRepository.save(demandPlanning);
    }


    @Override
    public void deleteDemandPlanning(String refDemandPlanning){

        DemandPlanning demandPlanToDelete=demandPlanningRepository.findByRefDemandPlanning(refDemandPlanning);
        LocalDate currentDate=LocalDate.now();
        if(demandPlanToDelete.getStartedDate().isBefore(currentDate))
        {
            throw new  MinistryOfCultureBusinessException(MinistryOfCultureMessage.AMC_PERIOD_PLANNING_ERROR);
        }
        demandPlanningRepository.delete(demandPlanToDelete);
    }


    @Override
    public DemandPlanning getByDemandPlanningRef(String refDemandPlanning) {
        return demandPlanningRepository.findByRefDemandPlanning(refDemandPlanning);
    }


    @Override
    public Page<DemandPlanning> getAllPlannings(Pageable pageable) {
        return demandPlanningRepository.findAll(pageable);
    }


    @Override
    public Page<DemandPlanning> getDemandPlanningBySearchCriteria(DemandPlanningPage demandPlanningPage, DemandPlanningCardCriteria demandPlanningCardCriteria) {
        return demandPlanningCriteriaRepository.findAllDemandPlannigByFilter(demandPlanningPage,demandPlanningCardCriteria);

    }


    @Override
    public DemandPlanning findDemandPlanningByStatus(StatusEnum open) {
        return demandPlanningRepository.findByStatus(open);
    }

}
