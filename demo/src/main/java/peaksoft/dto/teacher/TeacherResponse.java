package peaksoft.dto.teacher;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Course;


@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Builder
public class TeacherResponse {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private Course course;
}
