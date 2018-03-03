package model;

public class StatementException extends RuntimeException {
    public StatementException(String message) {
        super(message);
    }

    public StatementException(String message, Exception e) {
        super(message, e);
    }
}
