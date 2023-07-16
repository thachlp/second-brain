package coffee.shop.model.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -1124744436047637162L;

    public NotFoundException(String message) {
        super(message);
    }
}
