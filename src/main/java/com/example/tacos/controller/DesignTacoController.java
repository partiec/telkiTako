package com.example.tacos.controller;

import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Ingredient.Type;
import com.example.tacos.model.Taco;
import com.example.tacos.model.TacoOrder;
import com.example.tacos.repository.IngredientRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients =  ingredientRepo.findAll();
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType((List<Ingredient>) ingredients, type));
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
