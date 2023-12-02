package games;

public class FizzBuzz {

    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";

    private FizzBuzz() {
    }

    public static String convert(Integer input) throws OutOfRangeException {
        if (input <= 0) {
            throw new OutOfRangeException();
        }
        if (input > 100) {
            throw new OutOfRangeException();
        }

        if (isFizz(input) && isBuzz(input)) {
            return FIZZ + BUZZ;
        }
        if (isFizz(input)) {
            return FIZZ;
        }
        if (isBuzz(input)) {
            return BUZZ;
        }
        return input.toString();
    }

    private static boolean isFizz(Integer input) {
        return input % 3 == 0;
    }

    private static boolean isBuzz(Integer input) {
        return input % 5 == 0;
    }
}
