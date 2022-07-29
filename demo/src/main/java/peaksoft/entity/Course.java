package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(generator =  "course_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private int duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    @JsonIgnore

    private Company company;
}