package spring.test.mentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class MentorDtoPutResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("identificationNumber")
    private String identificationNumber;

    @JsonProperty("team")
    private String team;

    @JsonProperty("rating")
    private Float rating;

    @JsonProperty("cohort_id")
    private Long cohortId;
}
