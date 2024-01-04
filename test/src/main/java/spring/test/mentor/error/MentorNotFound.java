package spring.test.mentor.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MentorNotFound extends RuntimeException {
    public MentorNotFound() {
        super("no mentor found in the database with the provided id");
    }
}
