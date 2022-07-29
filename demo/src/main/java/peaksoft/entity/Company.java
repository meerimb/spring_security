package peaksoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(generator = "company_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    private String companyName;
    private String locatedCountry;
    private LocalDateTime created;

    @OneToMany(cascade = ALL ,
            fetch = FetchType.EAGER,mappedBy = "company")
    @JsonIgnore
    private List<Course> course;
}

