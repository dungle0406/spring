package spring.test.mentor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidIdBadRequest extends RuntimeException {
    public InvalidIdBadRequest() {
        super("identification should be between 1 and 5");
    }
}
