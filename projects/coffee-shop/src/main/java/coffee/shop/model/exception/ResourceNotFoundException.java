package coffee.shop.model.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1124744436047637162L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
