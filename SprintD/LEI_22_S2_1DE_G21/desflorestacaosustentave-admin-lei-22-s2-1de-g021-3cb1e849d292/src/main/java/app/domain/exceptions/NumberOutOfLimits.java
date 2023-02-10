package app.domain.exceptions;

public class NumberOutOfLimits extends  RuntimeException {

    public NumberOutOfLimits(String message) {
        super(message);
    }
}
