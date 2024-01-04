package spring.test.mentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class MentorDtoRequest {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("identificationNumber")
    private String identificationNumber;

    @JsonProperty("team")
    private String team;

    @JsonProperty( "rating")
    private Float rating;
}
