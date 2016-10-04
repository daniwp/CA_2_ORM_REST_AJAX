package exceptions;

/**
 *
 * @author Daniel
 */
public class PrettyException {
    
    private int code;
    private String message;

    public PrettyException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
