package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class FrontpageController {

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("title", "Главная страница сайта!");
        return "index";
    }
}
