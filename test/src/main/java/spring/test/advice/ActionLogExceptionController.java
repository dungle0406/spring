package spring.test.advice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring.test.mentor.error.InvalidRatingBadRequest;
import spring.test.mentor.error.LackOfInformation;
import spring.test.mentor.error.MentorNotFound;

import java.time.Instant;

@RestControllerAdvice
public class ActionLogExceptionController {
    private final ActionLogService actionLogService;

    @Autowired
    public ActionLogExceptionController(ActionLogService logService) {
        this.actionLogService = logService;
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


    @ExceptionHandler({InvalidRatingBadRequest.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ActionLogMessage handleInvalidRatingBadRequest(HttpServletRequest request, RuntimeException ex) {
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
    @ExceptionHandler({LackOfInformation.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ActionLogMessage handleLackOfInformation(HttpServletRequest request, Exception ex) {
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
