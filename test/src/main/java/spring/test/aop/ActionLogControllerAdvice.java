package spring.test.aop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.test.mentor.error.LackOfInformation;

import java.time.Instant;

@RestControllerAdvice
public class ActionLogControllerAdvice {
    private final ActionLogService actionLogService;

    @Autowired
    public ActionLogControllerAdvice(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }

    @ExceptionHandler({LackOfInformation.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ActionLog handleMentorNotFoundException(HttpServletRequest request, RuntimeException ex) {
        ActionLog actionLog = ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.NOT_FOUND.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();

        actionLogService.addNewActionLog(actionLog);

        return actionLog;
    }
}
