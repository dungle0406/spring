package spring.test.actionlog;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class ActionLogService {
    private final List<ActionLog> logServices = new ArrayList<>();

    public void addNewLogService(ActionLog logMessage) {
        logServices.add(logMessage);
    }
}
