package com.example.tacos;

import com.example.tacos.Ingredient;
import lombok.Data;

import java.util.List;

@Data
public class Taco {
    private String name;
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
