package coffee.shop.model.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 7537768695790147486L;

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
