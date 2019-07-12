package ru.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Competition;
import ru.itpark.domain.Pair;
import ru.itpark.dto.CompetitionAdd;
import ru.itpark.enumeration.CompetitionType;
import ru.itpark.exception.UploadFileException;
import ru.itpark.repository.CompetitionRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompetitionService {
    private final CompetitionRepository repository;
    private final FileService fileService;

    public List<Competition> findAll() {
        return repository.findAll();
    }

    public void save(CompetitionAdd dto) {
        // TODO:  если нет файла
        if (dto.getMedia() == null) {
            // TODO: а если name == null?
            // if (dto.getName() == null) { throw new NameCantBeNullNoteException
            repository.save(new Competition(
                            0,
                            dto.getName(),
                            CompetitionType.REGULAR,
                            null
                    )
            );
            return;
        }

        try {
            Pair<CompetitionType, String> saved = fileService.save(dto.getMedia());

            repository.save(new Competition(
                            0,
                            dto.getName(),
                            saved.getFirst(),
                            saved.getSecond()
                    )
            );
        } catch (IOException e) {
            throw new UploadFileException(e);
        }
    }
}
