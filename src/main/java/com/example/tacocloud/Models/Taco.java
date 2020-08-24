package com.example.tacocloud.Models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;

    private Date createAt;

    @NotNull
    @Size(min = 5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min = 3,  message = "You must choose at least 3 ingredient")
    private List<String> ingredients;
}
