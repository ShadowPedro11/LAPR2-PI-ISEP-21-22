package app.domain.exceptions;

public class NoVaccinationCenterWasFound extends RuntimeException {

    public NoVaccinationCenterWasFound(String message) {
        super(message);
    }
}
