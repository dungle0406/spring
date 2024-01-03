package spring.test.mentor.aop;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class LoggingService {
    private final List<LogMessage> logServices = new ArrayList<>();

    public void addNewLogService(LogMessage logMessage) {
        logServices.add(logMessage);
    }
}
