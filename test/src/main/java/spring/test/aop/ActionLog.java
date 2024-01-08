package spring.test.aop;

import lombok.*;

import java.time.Instant;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ActionLog {
    private String path;
    private String method;
    private Instant time;
    private int responseStatusCode;
    private String message;
}
