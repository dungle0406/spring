package spring.test.mentor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRatingBadRequest extends RuntimeException {
    public InvalidRatingBadRequest() {
        super("rating should be in range from 1 to 5");
    }
}
