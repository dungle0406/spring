package spring.test.mentor.module;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "mentors")
@Getter
@Setter
@Accessors(chain = true)
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "identificationNumber")
    private String identificationNumber;

    @Column(name = "team")
    private String team;

    @Column(name = "rating")
    private String rating;
}
