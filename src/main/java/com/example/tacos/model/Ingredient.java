package com.example.tacos.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
@NoArgsConstructor
public class Ingredient {
    @Id
    private String id;
    private String name;
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}