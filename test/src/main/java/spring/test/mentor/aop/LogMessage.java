package spring.test.mentor.aop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogMessage {
    private String path;
    private String method;
    private Instant time;
    private int responseStatusCode;
}
