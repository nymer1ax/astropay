package co.com.astropay.usecase.exceptions.custom;

public class NoContentException extends RuntimeException{
    public NoContentException(String message) {
        super(message);
    }
}
