package com.easybank.loans.controller;

import com.easybank.loans.constants.LoansConstants;
import com.easybank.loans.dto.LoansContactInfoDto;
import com.easybank.loans.dto.LoansDto;
import com.easybank.loans.dto.ResponseDto;
import com.easybank.loans.service.ILoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping(value = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class LoanController {

    private final ILoanService iLoanService;

    @Value("${build.version}")
    private String buildVersion;

    private final Environment environment;

    private final LoansContactInfoDto loansContactInfoDto;
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digits")
                                                  String mobileNumber){

        iLoanService.createLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201,LoansConstants.MESSAGE_201));
    }
    
    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam
                                                     @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digits")
                                                     String mobileNumber){
        LoansDto loansDto = iLoanService.fetchLoan(mobileNumber);

        return ResponseEntity.ok(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestBody @Valid LoansDto loansDto){

        boolean isUpdated = iLoanService.updateLoan(loansDto);

        if(isUpdated){
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoan(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digits")
                                                      String mobileNumber){
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDto(LoansConstants.STATUS_200,LoansConstants.MESSAGE_200));

        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_DELETE));
        }
    }

    @GetMapping("build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("java-version")
    public ResponseEntity<String> getJavaVersion(){
        return ResponseEntity.ok(environment.getProperty("JAVA_HOME"));
    }

    @GetMapping("contact-info")
    public ResponseEntity<LoansContactInfoDto> getContactInfo(){
        return ResponseEntity.ok(loansContactInfoDto);
    }
}
