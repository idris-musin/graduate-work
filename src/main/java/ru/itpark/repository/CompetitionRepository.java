package ru.itpark.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.itpark.domain.Competition;
import ru.itpark.enumeration.CompetitionType;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompetitionRepository {
    private final NamedParameterJdbcTemplate template;

    public List<Competition> findAll() {
        return template.query(
                "SELECT id, name, type, media FROM competitions",
                (rs, rowNum) -> new Competition(
                        rs.getInt("id"),
                        rs.getString("name"),
                        // enum -> ordinal - порядковый номер, value - строка
                        CompetitionType.valueOf(rs.getString("type")),  // valueOf делает из строкового значения конкретное значение перечисление
                        rs.getString("media")
                )
        );
    }

    public void save(Competition model) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", model.getName());
        params.addValue("type", model.getType().name());  // enum -> String
        params.addValue("media", model.getMedia());

        template.update(
                "INSERT INTO competitions (name, type, media) VALUES (:name, :type, :media)",
                // в Map.of нельзя класть null
                params
        );
    }
}
