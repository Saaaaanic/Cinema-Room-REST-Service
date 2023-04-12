package cinema.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class WrongTokenException extends RuntimeException {
    public WrongTokenException() {
        super("Wrong token!");
    }
}
