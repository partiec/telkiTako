package com.example.tacos.controller;

import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Ingredient.Type;
import com.example.tacos.model.Taco;
import com.example.tacos.model.TacoOrder;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @ModelAttribute
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
            // в модель кладутся аттрибуты:
            // ключ    -    значение
            //"wrap"   -    Iterable<Ingredients> типа Type.WRAP
            //"protein"   -    Iterable<Ingredients> типа Type.PROTEIN
            //"veggies"   -    Iterable<Ingredients> типа Type.VEGGIES
            //"cheese"   -    Iterable<Ingredients> типа Type.CHEESE
            //"sauce"   -    Iterable<Ingredients> типа Type.SAUCE
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
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



    // Когда ФОРМА заполнена и нажата кнопка |Submit| -->
    // Поля формы присваиваются полям объекта Taco,
    // который передается в качестве параметра методу с аннотацией @PostMapping.
    // Получив этот объект, метод может делать с ним все, что захочет.
    // В данном случае он добавляет объект Taco в объект TacoOrder,
    // который также передается в параметре,
    // а затем записывает его в журнал.
    // Аннотация @ModelAttribute перед параметром TacoOrder указывает, что
    // он должен использовать объект TacoOrder, который был помещен в модель
    // методом @ModelAttribute order()
    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors,  @ModelAttribute TacoOrder tacoOrder){

        if (errors.hasErrors()){
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);

        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
