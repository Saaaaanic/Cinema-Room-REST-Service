package cinema.exception;

public class OutOfBoundsCoordinatesException extends RuntimeException {
    public OutOfBoundsCoordinatesException() {
        super("The number of a row or a column is out of bounds!");
    }
}
