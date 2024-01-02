package spring.test.cohort.service;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.test.cohort.dto.CohortDtoResponse;
import spring.test.cohort.entity.Cohort;

import java.util.List;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    CohortDtoResponse findByName(String cohortName);
}
