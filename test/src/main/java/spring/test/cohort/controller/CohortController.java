package spring.test.cohort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.cohort.dto.CohortDtoRequest;
import spring.test.cohort.dto.CohortDtoResponse;
import spring.test.cohort.service.CohortService;

import java.util.List;

@RestController
@RequestMapping("/api/cohorts")
public class CohortController {
    private final CohortService cohortService;

    @Autowired
    public CohortController(CohortService cohortService) {
        this.cohortService = cohortService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void creatCohort(@RequestBody CohortDtoRequest cohortDtoRequest) {
        cohortService.creatCohort(cohortDtoRequest);
    }

    @GetMapping
    public List<CohortDtoResponse> getCohorts() {
        return cohortService.findAll();
    }

    @GetMapping("/{cohortName}")
    public CohortDtoResponse getCohortByName(@PathVariable String cohortName) {
        return cohortService.findCohortByName(cohortName);
    }
}
