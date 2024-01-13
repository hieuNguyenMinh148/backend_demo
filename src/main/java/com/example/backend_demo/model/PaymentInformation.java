package com.example.backend_demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {

    private String cardholderName;

    private String cardNumber;

    private LocalDate expirationDate;

    private String cvv;
}
