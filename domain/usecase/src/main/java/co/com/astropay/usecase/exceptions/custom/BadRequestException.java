package co.com.astropay.usecase.exceptions.custom;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
