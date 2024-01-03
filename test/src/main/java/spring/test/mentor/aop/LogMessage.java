package spring.test.mentor.aop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String path;
    private String method;
    private Instant time;
    private int responseStatusCode;
    private String message;
}
