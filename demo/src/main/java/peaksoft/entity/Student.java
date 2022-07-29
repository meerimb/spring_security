package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen", sequenceName = "student_seq", allocationSize = 1)
    private Long id;

    private String firstname;
    private String lastname;

    private String email;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;
}
