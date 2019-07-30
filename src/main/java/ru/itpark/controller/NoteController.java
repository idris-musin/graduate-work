package ru.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itpark.dto.NoteAdd;
import ru.itpark.service.NoteService;

@Controller
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("title", "Новости");
        model.addAttribute("notes", service.findAll());
        return "notes";
    }

//    @PreAuthorize("hasRole('ADMIN') || @noteService.isOwner(#id, principal.getId())")
    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var note = service.findById(id);
        model.addAttribute("title", note.getName());
        model.addAttribute("note", note);
        return "note";
    }

    @PreAuthorize("hasRole('ADMIN') || @noteService.isOwner(#id, principal.getId())")
    @PostMapping("/{id}/remove")
    public String removeById(@PathVariable int id) {
        service.removeById(id);
        return "redirect:/notes";
    }

    @PreAuthorize("hasRole('ADMIN') || @noteService.isOwner(#id, principal.getId())")
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("title", "Страница добавления новостей");
        return "note-add";
    }

    @PreAuthorize("hasRole('ADMIN') || @noteService.isOwner(#id, principal.getId())")
    @PostMapping("/add")
    public String add(@ModelAttribute NoteAdd dto) {
        service.add(dto);
        return "redirect:/notes";
    }
}
