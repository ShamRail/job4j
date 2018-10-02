package nonblocking;

public class OptimisticException extends RuntimeException {
    public OptimisticException() {
        super("Optimistic error");
    }
}
