package com.example.tacos;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyController {

    @ModelAttribute(name = "cat")
    public MyCat sendCat() {
        return new MyCat();
    }

    @GetMapping
    public String getViewName() {
        return "myGet";
    }

    @PostMapping
    public String getName(MyCat myCat, Model model) {
        model.addAttribute("nameNeed", myCat.getName());

        return "myShow";
    }


}
