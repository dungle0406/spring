package spring.test.cohort.service;

import org.mapstruct.Mapper;
import spring.test.cohort.dto.CohortDtoRequest;
import spring.test.cohort.dto.CohortDtoResponse;
import spring.test.cohort.entity.Cohort;

@Mapper(componentModel = "spring")
public interface CohortMapper {
    Cohort mapCohortFromRequest(CohortDtoRequest cohortDtoRequest);
    CohortDtoResponse mapResponseFromCohort(Cohort cohort);
}
