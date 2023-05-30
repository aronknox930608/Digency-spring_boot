package ma.digency.gov.amc.controller;

import ma.digency.gov.amc.dto.CommissionVoteRequest;
import ma.digency.gov.amc.dto.shared.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("commissions/")
public interface CommissionController {

    @PostMapping
    ResponseEntity<CommissionResponse> createCommission(@RequestBody CommissionRequest request);

    @GetMapping
    ResponseEntity<List<CommissionResponse>> findAllCommission();

    @PutMapping("{refCommission}/")
    ResponseEntity<CommissionResponse> updateCommission(@PathVariable("refCommission") String refCommission,
                                                        @RequestBody CommissionResponse request);

    @GetMapping("{refCommission}/")
    ResponseEntity<CommissionResponse> findCommission(@PathVariable("refCommission") String refCommission);


    @GetMapping("members/")
    ResponseEntity<List<CommissionMemberResponse>> findAllCommissionMembers();

    @PostMapping("{refCommission}/members/")
    ResponseEntity<List<CommissionMemberResponse>> createCommissionMembers(@PathVariable("refCommission") String refCommission
            , @RequestBody List<CommissionMemberRequest> members);

    @PutMapping("{refCommission}/members/")
    ResponseEntity<Void> addCommissionMembers(@PathVariable("refCommission") String refCommission
            , @RequestBody ReferenceRequest refMembers);

    @GetMapping("{refCommission}/members/")
    ResponseEntity<List<CommissionMemberResponse>> findCommissionMembers(@PathVariable("refCommission") String refCommission);


    @PutMapping("{refCommission}/planning/{refPlanning}")
    ResponseEntity<Void> addPublicationsToPlanning(@PathVariable("refCommission") String refCommission,
                                                   @PathVariable("refPlanning") String refPlanning,
                                                   @RequestBody ReferenceRequest refs);


    @PostMapping("{refCommission}/planning/{refPlanning}/vote")
    ResponseEntity<Void> votePublication(@PathVariable("refCommission") String refCommission,
                                         @PathVariable("refPlanning") String refPlanning,
                                         @RequestBody CommissionVoteRequest refs);

}
