package niccolosciucco.u5_w1_d5.exceptions;

public class AlreadyInDb extends RuntimeException {
    public AlreadyInDb(String message) {
        super(message);
    }
}
