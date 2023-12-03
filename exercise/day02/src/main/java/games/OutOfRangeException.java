package games;

public class OutOfRangeException extends Exception {

    private final Integer input;

    public OutOfRangeException(Integer input) {
        this.input = input;
    }

    @Override
    public String toString() {
        return input + "is out of range";
    }
}
