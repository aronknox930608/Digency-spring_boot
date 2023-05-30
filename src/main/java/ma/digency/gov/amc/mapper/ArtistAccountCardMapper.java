package ma.digency.gov.amc.mapper;

import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.IoC;
import fr.xebia.extras.selma.Mapper;
import ma.digency.gov.amc.dto.card.ArtistAccountCardRequest;
import ma.digency.gov.amc.dto.card.ArtistAccountCardResponse;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountRequest;
import ma.digency.gov.amc.dto.proposalproject.ArtistAccountResponse;
import ma.digency.gov.amc.repository.entity.proposalproject.ArtistAccount;

@Mapper(withIoC = IoC.SPRING, withIgnoreMissing = IgnoreMissing.ALL, withIgnoreNullValue = true)
public interface ArtistAccountCardMapper {

    ArtistAccountCardResponse artistAccountToArtistAccountResponse(ArtistAccount artistAccount);
    ArtistAccount artistAccountRequestToArtistAccount(ArtistAccountCardRequest artistRequest);
    ArtistAccount updateArtistRequestToArtistAccount(ArtistAccountCardRequest artistAccountRequest, ArtistAccount find);



}
