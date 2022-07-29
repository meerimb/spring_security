package peaksoft.dto.student;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.StudyFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)

public class StudentRequest {

    private String firstname;
    private String lastname;
    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private Long groupId;
}
