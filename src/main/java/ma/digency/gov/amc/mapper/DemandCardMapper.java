package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.card.DemandCardRequest;
import ma.digency.gov.amc.dto.card.DemandCardResponse;
import ma.digency.gov.amc.repository.entity.artistCard.DemandCard;

@Mapper(withIoC = IoC.SPRING,
        withIgnoreMissing = IgnoreMissing.ALL,
        withIgnoreNullValue = true
)
public interface DemandCardMapper {
    DemandCard demandRequestToDemand(DemandCardRequest demandRequest);
    DemandCardResponse demandToDemandResponse(DemandCard demandCard);
    DemandCard updateDemandResponseToDemand(DemandCardResponse newDemand, DemandCard oldDemandCard);


}