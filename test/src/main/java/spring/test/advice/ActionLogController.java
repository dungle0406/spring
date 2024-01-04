package spring.test.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/actionlog")
@Slf4j
public class ActionLogController {
    private final ActionLogService actionLog;

    @Autowired
    public ActionLogController(ActionLogService actionLog) {
        this.actionLog = actionLog;
    }

    @GetMapping()
    public List<ActionLog> getActionLogs(@RequestParam(required = false) String type) {
        log.debug(type);

        List<ActionLog> actionLogs = actionLog.getLogServices();
        log.debug(actionLogs.toString());

        List<ActionLog> filteredActionLogs = actionLogs.stream()
                .filter(log -> log.getMethod().equals(type))
                .toList();
        log.debug(filteredActionLogs.toString());

        return filteredActionLogs;

    }
}
