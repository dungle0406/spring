package spring.test.log;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spring.test.mentor.error.InvalidIdBadRequest;
import spring.test.mentor.error.MentorNotFound;
import spring.test.mentor.error.UsedIdBadRequest;

import java.time.Instant;

@RestControllerAdvice
public class ActionLogExceptionController {
    private final ActionLogService actionLogService;

    @Autowired
    public ActionLogExceptionController(ActionLogService actionLogService) {
        this.actionLogService = actionLogService;
    }

    @ExceptionHandler({MentorNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ActionLogMessage handleMentorNotFoundException(HttpServletRequest request, RuntimeException ex) {
        ActionLog actionLog = ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.NOT_FOUND.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();

        actionLogService.addNewActionLog(actionLog);

        return new ActionLogMessage(ex.getMessage());
    }

    @ExceptionHandler({UsedIdBadRequest.class, InvalidIdBadRequest.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ActionLogMessage handleUsedIdBadRequestException(HttpServletRequest request, RuntimeException ex) {
        ActionLog actionLog = ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.BAD_REQUEST.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();

        actionLogService.addNewActionLog(actionLog);

        return new ActionLogMessage(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ActionLogMessage handleGlobalException(HttpServletRequest request, Exception ex) {
        ActionLog actionLog = ActionLog.builder()
                .path(request.getRequestURI())
                .method(request.getMethod())
                .responseStatusCode(HttpStatus.METHOD_NOT_ALLOWED.value())
                .time(Instant.now())
                .message(ex.getMessage())
                .build();

        actionLogService.addNewActionLog(actionLog);

        return new ActionLogMessage(ex.getMessage());
    }
}
