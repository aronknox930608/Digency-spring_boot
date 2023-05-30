package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.shared.GeneralMemberRequest;
import ma.digency.gov.amc.dto.shared.GeneralMemberResponse;
import ma.digency.gov.amc.process.GeneralMemberProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Tag(name = "GeneralMember")
public class GeneralMemberControllerImpl implements GeneralMemberController {

	@Autowired
	GeneralMemberProcess generalMemberProcess;


	@Override
	public ResponseEntity<GeneralMemberResponse> createGeneralMember(GeneralMemberRequest generalMemberRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(generalMemberProcess.createGeneralMember(generalMemberRequest));
	}

	@Override
	public ResponseEntity<List<GeneralMemberResponse>> findAllByRefParent(String refParent) {
		return ResponseEntity.status(HttpStatus.OK).body(generalMemberProcess.findAllByRefParent(refParent));
	}

	@Override
	public ResponseEntity<GeneralMemberResponse> updateGeneralMember(String refGeneralMember, GeneralMemberRequest generalMemberRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(generalMemberProcess.updateGeneralMember(refGeneralMember,generalMemberRequest));
	}

	@Override
	public ResponseEntity<Void> deleteGeneralMember(String refGeneralMember) {
		return ResponseEntity.status(HttpStatus.OK).body(generalMemberProcess.deleteGeneralMember(refGeneralMember));
	}


}
