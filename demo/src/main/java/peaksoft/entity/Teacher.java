package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(generator = "teacher_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "teacher_gen", sequenceName = "teacher_seq", allocationSize = 1)

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;


}
