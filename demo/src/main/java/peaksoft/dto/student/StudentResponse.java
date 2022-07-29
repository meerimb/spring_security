package peaksoft.dto.student;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Group;
import peaksoft.entity.StudyFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class StudentResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Group group;
}
