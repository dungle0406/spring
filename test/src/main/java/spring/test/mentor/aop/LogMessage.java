package spring.test.mentor.aop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.time.Instant;
@Getter
@AllArgsConstructor
@Builder
public class LogMessage {
    private String path;
    private String method;
    private Instant time;
    private int responseStatusCode;
}
