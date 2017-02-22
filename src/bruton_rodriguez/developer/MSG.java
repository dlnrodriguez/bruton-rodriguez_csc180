package bruton_rodriguez.developer;

/**
 * Enum that contains the different message types.
 */
public enum MSG {
    SUCC("Success");

    String msg;

    MSG(String msg) {
        this.msg = msg;
    }


    @Override
    public String toString() {
        return msg;
    }
}
