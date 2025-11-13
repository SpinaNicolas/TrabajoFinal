package Excepciones;

public class ErrorDuplicado extends RuntimeException {
    public ErrorDuplicado(String message) {
        super(message);
    }
}
