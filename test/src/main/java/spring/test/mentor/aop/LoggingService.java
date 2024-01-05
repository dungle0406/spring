package spring.test.mentor.aop;

import lombok.Getter;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class LoggingService {
    private final List<LogMessage> logServices = new ArrayList<>();
    private final TaskExecutor taskExecutor;

    public void addNewLogService(LogMessage logMessage) {
        logServices.add(logMessage);

        taskExecutor.execute(() -> mongoRepo.save(logMessage));
        mongoRepo.save(logMessage); // -> throw exception
    }
}
