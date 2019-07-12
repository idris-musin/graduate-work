package ru.itpark.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import ru.itpark.domain.Pair;
import ru.itpark.enumeration.CompetitionType;
import ru.itpark.exception.UnsupportedFileTypeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileService {
    public static final String mediaPath = "media";

    public FileService() throws IOException {
        if (!Files.exists(Path.of(".", mediaPath))) {
            Files.createDirectory(Path.of(".", mediaPath));
        }
    }

    public ResponseEntity<Resource> getByName(String name) throws IOException {
        Path path = Path.of(".", mediaPath, name);
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(path));

        return new ResponseEntity<>(
                new FileSystemResource(path),
                headers,
                HttpStatus.OK
        );
    }

    public Pair<CompetitionType, String> save(MultipartFile file) throws IOException {
        String name = UUID.randomUUID().toString();
        CompetitionType type = CompetitionType.REGULAR;
        String contentType = file.getContentType();

        if (contentType == null) {
            throw new UnsupportedFileTypeException();
        }

        if (contentType.equals(MediaType.IMAGE_JPEG_VALUE)) {
            type = CompetitionType.IMAGE;
            name += ".jpg";
        } else if (contentType.equals(MediaType.IMAGE_PNG_VALUE)) {
            type = CompetitionType.IMAGE;
            name += ".png";
        } else if (contentType.equals("audio/mp3")) {
            type = CompetitionType.AUDIO;
            name += ".mp3";
        } else if (contentType.equals("video/x-ms-wmv")) {
            type = CompetitionType.VIDEO;
            name += ".mp4";
        } else {
            throw new UnsupportedFileTypeException(contentType);
        }

        file.transferTo(Path.of(".", mediaPath, name));

        return new Pair<>(type, name);
    }

}
