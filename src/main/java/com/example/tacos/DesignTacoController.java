package com.example.tacos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import com.example.tacos.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {


    // вызывается раньше, добавляет в Model атрибуты: "wrap", "protein", "veggies", "cheese", "sauce". В каждом - список из двух! ингридиентов данного типа
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }


    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    // Получение аргумента из модели, а модель уже связана с представлением. Поле заполняется тем самым объектом из представления
    public String processTaco(
            @Valid Taco taco, Errors errors,
            @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    // возвращает List ингридиентов данного типа
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}


//original----------->
// package com.example.tacos.web;
//
//import com.example.tacos.Ingredient;
//import com.example.tacos.Taco;
//import com.example.tacos.TacoOrder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.example.tacos.Ingredient.Type;
//
//@Slf4j
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("tacoOrder")
//public class DesignTacoController {
//
//
//    @ModelAttribute
//    public void addIngredientsToModel(Model model) {
//        List<Ingredient> ingredients = Arrays.asList(
//                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
//                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
//                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
//                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
//                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//                new Ingredient("CHED", "Cheddar", Type.CHEESE),
//                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
//        );
//
//        Type[] types = Ingredient.Type.values();
//
//        for (Type type : types) {   // types = {WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE}
//            //model.addAttribute(type.toString().toLowerCase());                       //  WRONG !!! ';'
//            //filterByType(ingredients, type);                                         //  WRONG !!! ');'
//            model.addAttribute(type.toString().toLowerCase(),       // RIGHT
//            filterByType(ingredients, type));                       // RIGHT
//        }
//    }
//
//
//    @ModelAttribute(name = "tacoOrder")
//    public TacoOrder order() {
//        return new TacoOrder();
//    }
//
//
//    @ModelAttribute(name = "taco")
//    public Taco taco() {
//        return new Taco();
//    }
//
//
//    @GetMapping
//    public String showDesignForm() {
//        return "design";
//    }
//
//
//
//    private Iterable<Ingredient> filterByType (List<Ingredient> ingridients, Type type) {
//        return ingridients
//                .stream()
//                .filter(x -> x.getType().equals(type))
//                .collect(Collectors.toList());
//    }
//
//
//
//}
