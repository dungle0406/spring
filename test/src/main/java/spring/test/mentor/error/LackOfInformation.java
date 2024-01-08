package spring.test.mentor.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LackOfInformation extends RuntimeException {
}
