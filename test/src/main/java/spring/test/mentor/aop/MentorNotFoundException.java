package spring.test.mentor.aop;

import lombok.NoArgsConstructor;

import java.io.Serial;

@NoArgsConstructor
public class MentorNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
}
