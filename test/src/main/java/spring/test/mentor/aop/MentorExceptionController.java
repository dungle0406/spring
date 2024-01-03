package spring.test.mentor.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class MentorExceptionController {
    @ExceptionHandler(MentorNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public LogMessage mentorNotFoundException(HttpServletRequest request) {
        return LogMessage.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.NOT_FOUND.value())
                .time(Instant.now())
                .message("no mentor found in the database with the provided id")
                .build();
    }


    @ExceptionHandler(UsedIdBadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public LogMessage usedIdNotFoundException(HttpServletRequest request) {
        return LogMessage.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.BAD_REQUEST.value())
                .time(Instant.now())
                .message("identification number already used")
                .build();
    }
}
