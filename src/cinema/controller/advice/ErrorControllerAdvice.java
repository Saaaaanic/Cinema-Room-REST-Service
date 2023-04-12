package cinema.controller.advice;

import cinema.exception.AlreadyPurchasedException;
import cinema.exception.OutOfBoundsCoordinatesException;
import cinema.exception.WrongPasswordException;
import cinema.exception.WrongTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({
            AlreadyPurchasedException.class,
            OutOfBoundsCoordinatesException.class,
            WrongTokenException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ErrorDTO> handleException(Exception e) {

        return ResponseEntity.badRequest()
                .body(new ErrorDTO(e.getMessage()));
    }

    @ExceptionHandler({
            WrongPasswordException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED )
    ResponseEntity<ErrorDTO> handleException(WrongPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorDTO(e.getMessage()));

    }
    record ErrorDTO(String error) {

    }
}
