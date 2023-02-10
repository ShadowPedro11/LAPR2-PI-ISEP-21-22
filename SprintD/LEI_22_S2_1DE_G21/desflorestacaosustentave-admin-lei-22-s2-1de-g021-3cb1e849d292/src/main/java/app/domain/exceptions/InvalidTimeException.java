package app.domain.exceptions;

public class InvalidTimeException extends RuntimeException {

    public InvalidTimeException(String message) {
        super(message);
    }
}
