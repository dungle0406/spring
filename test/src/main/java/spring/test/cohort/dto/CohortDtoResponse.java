package spring.test.cohort.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import spring.test.mentor.dto.MentorDtoResponse;

import java.util.List;

@Getter
@Builder
@ToString
public class CohortDtoResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("mentors")
    private List<MentorDtoResponse> mentors;
}
