package peaksoft.dto.group;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public class GroupRequest {

        private String groupName;
        private LocalDate dateOfStart;
        private LocalDate dateOfFinish;
        private Long course;
    }

