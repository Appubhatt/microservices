package com.easybank.card.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Cards",
description = "Schema to hold Card information")
public class CardsDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description = "Mobile number of customer", example = "9874576657")
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Card Number must be 12 digits")
    @Schema(description = "Card Number of customer", example = "1544-5877-9986")
    private String cardNumber;

    @NotEmpty(message = "Card Type cannot be a null or empty")
    @Schema(description = "Card type of customer", example = "Debit Card/ Credit Card")
    private String cardType;

    @Positive(message = "totalLimit Should be greater than zero")
    @Schema(description = "Total limit available against a card", example = "100000")
    private int totalLimit;

    @PositiveOrZero(message = "Amount Used Should be greater or equal to zero")
    @Schema(description = "Total amount used by customer", example = "10000")
    private int amountUsed;

    @PositiveOrZero(message = "Available Amount should be greater or equal to zero")
    @Schema(description = "Total available amount against a card", example = "90000")
    private int availableAmount;
}
