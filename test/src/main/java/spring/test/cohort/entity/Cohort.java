package spring.test.cohort.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import spring.test.mentor.entity.Mentor;
import spring.test.mentor.entity.Mentor_;

import java.util.List;

@Entity
@Table(name = "cohort")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Cohort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = Mentor_.COHORT, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private List<Mentor> mentors;
}
