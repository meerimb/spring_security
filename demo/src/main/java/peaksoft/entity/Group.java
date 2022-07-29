package peaksoft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(generator = "group_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "group_gen", sequenceName = "group_seq", allocationSize = 1)

    private Long id;
    private String groupName;
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "group_courses", joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    List<Course> courses;
}
