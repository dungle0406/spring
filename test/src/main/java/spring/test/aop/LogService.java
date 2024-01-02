package spring.test.aop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class LogService {
    private final List<LogMessage> logs = new ArrayList<>();

    public void addLog(LogMessage log) {
        logs.add(log);
    }
}
