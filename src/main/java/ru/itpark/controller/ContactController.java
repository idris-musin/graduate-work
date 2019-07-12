package ru.itpark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("title", "Контакты");
        return "contacts";
    }
}
