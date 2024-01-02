package spring.test.mentor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class MentorPostResponse {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("givenRating")
    private Float rating;
}
