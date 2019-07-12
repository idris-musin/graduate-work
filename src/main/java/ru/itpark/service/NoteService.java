package ru.itpark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itpark.domain.Note;
import ru.itpark.dto.NoteAdd;
import ru.itpark.repository.NoteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository repository;

    public List<Note> findAll() {
        return repository.findAll();
    }

    public Note findById(int id) {
        return repository.findById(id);
    }

    public void save(Note note) {
        repository.save(note);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }

    public void likeById(int id) {
        repository.likeById(id);
    }

    public void dislikeById(int id) {
        repository.dislikeById(id);
    }

    public void add(NoteAdd dto) {
        var note = new Note(0, 1, dto.getName(), dto.getContent(), 0);
        save(note);
    }

    public boolean isOwner(int id, int userId) {
        return repository.isOwner(id, userId);
    }
}
