package spring.test.aop;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LogMessage {
    private String path;
    private String method;
    private Instant time;
    private Long responseStatusCode;

    public LogMessage(String path, String method, Long responseStatusCode) {
        this.path = path;
        this.method = method;
        this.time = Instant.now();
        this.responseStatusCode = responseStatusCode;
    }
}
