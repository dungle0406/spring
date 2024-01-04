package spring.test.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Builder
@AllArgsConstructor
public class ActionLog {
    private String path;
    private String method;
    private Instant time;
    private int responseStatusCode;
    private String message;
}
