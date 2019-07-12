package ru.itpark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itpark.service.FileService;

import java.io.IOException;

@Controller
@RequestMapping("/media")
@RequiredArgsConstructor
public class MediaController {
    private final FileService service;

    @GetMapping("/{name}")
    public ResponseEntity<Resource> getFile(@PathVariable String name) throws IOException {
        return service.getByName(name);
    }
}
