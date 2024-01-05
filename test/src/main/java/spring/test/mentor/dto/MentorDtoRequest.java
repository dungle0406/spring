package spring.test.mentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class MentorDtoRequest {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @NotNull
    @JsonProperty("identificationNumber")
    private String identificationNumber;

    @JsonProperty("team")
    private String team;

    @JsonProperty("rating")
    private Float rating;

    @JsonProperty("cohort_id")
    private Long cohortId;
}
