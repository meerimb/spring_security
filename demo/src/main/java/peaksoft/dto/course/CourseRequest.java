package peaksoft.dto.course;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CourseRequest {

    private String courseName;
    private int duration;
    private Long companyId;
}
