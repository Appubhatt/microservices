package com.easybank.loans.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class LoansDto {

    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be of 10 digits.")
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    private String mobileNumber;

    @NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number should be of 12 digits.")
    private String loanNumber;

    @NotEmpty(message = "Loan Type can not be null or empty")
    private String loanType;

    @Positive(message = "Total Loan amount should be greater than zero")
    private int totalLoan;

    @PositiveOrZero(message = "Total amount paid should be greater or equal to zero")
    private int amountPaid;

    @PositiveOrZero(message = "Outstanding amount should be greater or equal to zero")
    private int outstandingAmount;
}
