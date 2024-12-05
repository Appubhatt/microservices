package com.easybank.accounts.controller;

import com.easybank.accounts.constants.AccountsConstants;
import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountsController {

    private IAccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        accountService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }
}
