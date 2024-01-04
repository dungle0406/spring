package spring.test.mentor.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Mentor_Table")
@Getter
@Setter
@ToString
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "identificationNumber", unique = true)
    private String identificationNumber;

    @Column(name = "team")
    private String team;

    @Column(name = "rating")
    private int rating;
}
