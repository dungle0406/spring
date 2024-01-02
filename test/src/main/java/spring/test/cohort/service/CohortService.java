package spring.test.cohort.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.test.cohort.dto.CohortDtoRequest;
import spring.test.cohort.dto.CohortDtoResponse;
import spring.test.cohort.entity.Cohort;

import java.util.List;

@Service
@Slf4j
public class CohortService {
    private final CohortRepository cohortRepository;
    private final CohortMapper cohortMapper;

    @Autowired
    public CohortService(CohortRepository cohortRepository, CohortMapper cohortMapper) {
        this.cohortRepository = cohortRepository;
        this.cohortMapper = cohortMapper;
    }

    public void creatCohort(CohortDtoRequest cohortDtoRequest) {
        log.debug("Request: {}", cohortDtoRequest);

        Cohort cohort = cohortMapper.mapCohortFromRequest(cohortDtoRequest);
        log.debug("Cohort: {}", cohort);
        cohortRepository.save(cohort);
    }

    public List<CohortDtoResponse> findAll() {
        List<Cohort> cohorts = cohortRepository.findAll();
        log.debug("Cohort list: {}", cohorts);

        List<CohortDtoResponse> responses = cohorts.stream()
                .map(cohortMapper::mapResponseFromCohort)
                .toList();
        log.debug("Response: {}", responses);

        return responses;
    }

    public CohortDtoResponse findCohortByName(String cohortName) {
        CohortDtoResponse cohortDtoResponse = cohortRepository.findByName(cohortName);
        log.debug("Cohort list: {}", cohortDtoResponse);

        return cohortDtoResponse;
    }
}
