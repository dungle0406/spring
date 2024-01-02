package spring.test.cohort.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "no cohort found in the database with the provided id")
public class CohortNotFoundException extends RuntimeException{
}
