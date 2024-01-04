package spring.test.mentor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import spring.test.cohort.entity.Cohort;

import java.io.Serializable;

@Entity
@Table(name = "mentors")
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Mentor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "identificationNumber", nullable = false, unique = true)
    private String identificationNumber;

    @Column(name = "team", nullable = false)
    private String team;

    @Column(name = "rating", nullable = false)
    private Float rating;

    @Column(name = "cohort_id")
    private Long cohortId;

    @ManyToOne()
    @JoinColumn(name = "cohort_id", insertable = false, updatable = false)
    private Cohort cohort;
}
