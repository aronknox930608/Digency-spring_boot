package ma.digency.gov.amc.controller;


import ma.digency.gov.amc.dto.shared.GeneralMemberRequest;
import ma.digency.gov.amc.dto.shared.GeneralMemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RequestMapping("general-member/")
public interface GeneralMemberController {

    @PostMapping
    ResponseEntity<GeneralMemberResponse> createGeneralMember(@RequestBody GeneralMemberRequest generalMemberRequest);

    @GetMapping("{refParent}/")
    ResponseEntity<List<GeneralMemberResponse>> findAllByRefParent(@PathVariable @NotBlank String refParent);

    @PutMapping("{refGeneralMember}/")
    ResponseEntity<GeneralMemberResponse> updateGeneralMember(@PathVariable("refGeneralMember") @NotBlank String refGeneralMember
            ,@RequestBody GeneralMemberRequest generalMemberRequest);

    @DeleteMapping("{refGeneralMember}/")
    ResponseEntity<Void> deleteGeneralMember(@PathVariable("refGeneralMember") @NotBlank String refGeneralMember);
    

}
