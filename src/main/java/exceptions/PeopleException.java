package exceptions;

/**
 *
 * @author Daniel
 */
public class PeopleException extends Exception {

    private int code;

    public PeopleException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
