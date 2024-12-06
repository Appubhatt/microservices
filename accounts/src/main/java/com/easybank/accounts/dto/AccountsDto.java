package com.easybank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits.")
    @NotEmpty(message = "AccountNumber can not be empty or null.")
    private Long accountNumber;

    @NotEmpty(message = "AccountType can not be empty or null.")
    private String accountType;

    @NotEmpty(message = "BranchAddress can not be empty or null.")
    private String branchAddress;
}
