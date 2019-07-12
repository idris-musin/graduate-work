package ru.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.dto.CompetitionAdd;
import ru.itpark.service.CompetitionService;

@Controller
@RequestMapping("/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService service;

    @GetMapping
    public String all(Model model) {
        model.addAttribute("competitions", service.findAll());
        return "competitions";
    }

    @GetMapping("/add-form")
    public String addForm() {
        return "competition-add";
    }

    @PostMapping("/add-form")
    public String add(@ModelAttribute CompetitionAdd dto) {
        service.save(dto);
        return "redirect:/competitions";
    }
}
