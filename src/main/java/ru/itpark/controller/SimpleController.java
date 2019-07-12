package ru.itpark.controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/media")
public class SimpleController {

    @GetMapping("/simple/{name}")
    public ResponseEntity<Resource> getFile(@PathVariable String name) throws IOException {
        Path path = Path.of(".", name);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));

        return new ResponseEntity<>(
                new FileSystemResource(path),
                headers,
                HttpStatus.OK
        );
    }


    @GetMapping("/simple")
    public String simpleForm() {
        return "simple";
    }

    @PostMapping("/simple")
    public String simpleUpload(@RequestPart MultipartFile upload) throws IOException {
        upload.transferTo(Path.of(".", "file.png"));
        return "simple";
    }
}
