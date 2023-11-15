package be.bstorm.exceptions;

public class TitleBookException extends RuntimeException{
    public TitleBookException() {
        super("Title already taken");
    }

    public TitleBookException(String message) {
        super(message);
    }
}
