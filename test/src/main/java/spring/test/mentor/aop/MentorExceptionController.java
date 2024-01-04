package spring.test.mentor.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.actionlog.ActionLog;
import spring.test.actionlog.ActionLogService;

import java.time.Instant;

@RestControllerAdvice
public class MentorExceptionController {
    private final ActionLogService logService;

    @Autowired
    public MentorExceptionController(ActionLogService logService) {
        this.logService = logService;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ActionLog mentorNotFoundException(RuntimeException ex, HttpServletRequest request) {
        ActionLog logMessage = ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.NOT_FOUND.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();

        logService.addNewLogService(logMessage);
        return logMessage;
    }


    @ExceptionHandler(UsedIdBadRequest.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ActionLog usedIdNotFoundException(HttpServletRequest request) {
        ActionLog logMessage = ActionLog.builder()
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
    public ActionLog globalExceptionHandler(HttpServletRequest request) {
        ActionLog logMessage = ActionLog.builder()
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
