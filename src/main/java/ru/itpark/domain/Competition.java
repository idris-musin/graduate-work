package ru.itpark.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itpark.enumeration.CompetitionType;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition {
    private int id;
    private String name;
    private CompetitionType type;
    private String media;
}
