package ru.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Note;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Note> findAll() {
        return jdbcTemplate.query(
                "SELECT id, author_id, name, content, likes FROM notes",
                (rs, i) -> new Note(
                        rs.getInt("id"),
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("content"),
                        rs.getInt("likes")
                )
        );
    }

    public Note findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, author_id, name, content, likes FROM notes WHERE id = ?",
                new Object[]{id}, // массив из одного элемента, который и будет подставляться исходя из позиций ?
                (rs, i) -> new Note(
                        rs.getInt("id"), // rs.getInt(columnLabel) - по имени колонки, rs.getInt(index) - по индексу колонки, начиная с 1
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("content"),
                        rs.getInt("likes")
                )
        );
    }

    public void save(Note note) {
        if (note.getId() == 0) {
            jdbcTemplate.update(
                    "INSERT INTO notes (author_id, name, content) VALUES (?, ?, ?)",
                    note.getAuthorId(), note.getName(), note.getContent()
            );
        } else {
            jdbcTemplate.update(
                    "UPDATE notes SET name = ?, content = ? WHERE id = ?",
                    note.getName(), note.getContent(), note.getId()
            );
        }
    }

    public void removeById(int id) {
        jdbcTemplate.update(
                "DELETE FROM notes WHERE id = ?",
                id
        );
    }

    public void likeById(int id) {
        jdbcTemplate.update(
                "UPDATE notes SET likes = likes + 1 WHERE id = ?",
                id
        );
    }

    public void dislikeById(int id) {
        jdbcTemplate.update(
                "UPDATE notes SET likes = likes - 1 WHERE id = ?",
                id
        );
    }

    public boolean isOwner(int id, int userId) {
        return userId == jdbcTemplate.queryForObject(
                "SELECT author_id FROM notes WHERE id = ?",
                new Object[]{id},
                Integer.class
        );
    }
}
