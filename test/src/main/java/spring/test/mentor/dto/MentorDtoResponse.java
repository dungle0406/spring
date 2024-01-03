package spring.test.mentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class MentorDtoResponse {
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

    @JsonProperty( "rating")
    private int rating;
}
