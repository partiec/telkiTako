package com.example.taco.tacos;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
public class Taco {

    private String name;
    private List<Ingredient> ingredients;
}
