package ma.digency.gov.amc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ma.digency.gov.amc.dto.CommissionVoteRequest;
import ma.digency.gov.amc.dto.shared.*;
import ma.digency.gov.amc.process.CommissionProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Commission")
public class CommissionControllerImpl implements CommissionController {

    @Autowired
    private CommissionProcess commissionProcess;

    @Override
    public ResponseEntity<CommissionResponse> createCommission(CommissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commissionProcess.createCommission(request));
    }

    @Override
    public ResponseEntity<List<CommissionResponse>> findAllCommission() {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.findAllCommission());
    }

    @Override
    public ResponseEntity<CommissionResponse> updateCommission(String refCommission, CommissionResponse request) {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.updateCommission(refCommission, request));
    }

    @Override
    public ResponseEntity<CommissionResponse> findCommission(String refCommission) {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.findCommission(refCommission));
    }


    @Override
    public ResponseEntity<List<CommissionMemberResponse>> findAllCommissionMembers() {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.findAllCommissionMembers());
    }

    @Override
    public ResponseEntity<List<CommissionMemberResponse>> createCommissionMembers(String refCommission, List<CommissionMemberRequest> members) {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.createCommissionMembers(refCommission, members));
    }

    @Override
    public ResponseEntity<Void> addCommissionMembers(String refCommission, ReferenceRequest refMembers) {
        commissionProcess.addCommissionMembers(refCommission, refMembers.getRefs());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<CommissionMemberResponse>> findCommissionMembers(String refCommission) {
        return ResponseEntity.status(HttpStatus.OK).body(commissionProcess.findCommissionMembers(refCommission));
    }

    @Override
    public ResponseEntity<Void> addPublicationsToPlanning(String refCommission, String refPlanning, ReferenceRequest refs) {
        commissionProcess.addPublicationsToTreat(refCommission, refPlanning, refs);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> votePublication(String refCommission, String refPlanning, CommissionVoteRequest voteRequest) {
        commissionProcess.addVotePublication(refCommission,voteRequest,refPlanning);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
