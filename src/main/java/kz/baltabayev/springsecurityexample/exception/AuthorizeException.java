package kz.baltabayev.springsecurityexample.exception;

public class AuthorizeException extends RuntimeException{
    public AuthorizeException(String message) {
        super(message);
    }
}
