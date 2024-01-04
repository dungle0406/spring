package spring.test.log;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Getter
public class ActionLogService {
    private final List<ActionLog> actionLogs = new ArrayList<>();

    public void addNewActionLog(ActionLog actionLog) {
        actionLogs.add(actionLog);
    }
}
