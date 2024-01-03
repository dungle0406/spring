package spring.test.mentor.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class MentorExceptionController {
    private final LoggingService logService;

    @Autowired
    public MentorExceptionController(LoggingService logService) {
        this.logService = logService;
    }

    @ExceptionHandler(MentorNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public LogMessage mentorNotFoundException(HttpServletRequest request) {
        LogMessage logMessage = LogMessage.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.NOT_FOUND.value())
                .time(Instant.now())
                .message("no mentor found in the database with the provided id")
                .build();

        logService.addNewLogService(logMessage);
        return logMessage;
    }


    @ExceptionHandler(UsedIdBadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public LogMessage usedIdNotFoundException(HttpServletRequest request) {
        LogMessage logMessage = LogMessage.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.BAD_REQUEST.value())
                .time(Instant.now())
                .message("identification number already used")
                .build();

        logService.addNewLogService(logMessage);
        return logMessage;
    }

    @ExceptionHandler(Exception.class)
    public LogMessage globalExceptionHandler(HttpServletRequest request) {
        LogMessage logMessage = LogMessage.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .time(Instant.now())
                .message("method is not available")
                .build();

        logService.addNewLogService(logMessage);
        return logMessage;
    }
}
