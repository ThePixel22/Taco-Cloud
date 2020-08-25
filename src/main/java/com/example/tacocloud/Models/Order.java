package com.example.tacocloud.Models;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long id;

    private Date place_At;

    @NotBlank(message = "Name is required")
    private String delivery_Name;

    @NotBlank(message = "Street is required")
    private String delivery_Street;

    @NotBlank(message = "city is required")
    private String delivery_City;

    @NotBlank(message = "State is required")
    private String delivery_State;

    @NotBlank(message = "Zip code is required")
    private String delivery_Zip;

    @CreditCardNumber(message = "Not a valid credit card number")
    private String cc_Number;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
    private String cc_Expiration;

    @Digits(integer = 3 , fraction = 0, message = "Invalid CVV")
    private String cc_CVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco saved) {
        tacos.add(saved);
    }
}
