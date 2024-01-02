package spring.test.cohort.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Builder
@ToString
public class CohortDtoRequest {
    private String name;

    @JsonCreator
    public CohortDtoRequest(String name) {
        this.name = name;
    }
}
