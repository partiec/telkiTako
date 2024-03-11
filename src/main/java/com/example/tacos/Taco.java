package com.example.tacos;

import com.example.tacos.Ingredient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class Taco {

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long.")
    private String name;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 ingredient.")
    private List<Ingredient> ingredients;

}







//original-------------->
// package com.example.taco.tacos;
//
//
//import lombok.Data;
//
//import java.util.List;
//
//
//@Data
//public class Taco {
//
//    private String name;
//    private List<Ingredient> ingredients;
//}
