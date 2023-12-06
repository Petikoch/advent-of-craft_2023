package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {

    @ParameterizedTest
    @CsvSource({
            "-1, OutOfRangeException",
            "0, OutOfRangeException",
            "1, 1",
            "2, 2",
            "3, Fizz",
            "4, 4",
            "5, Buzz",
            "6, Fizz",
            "7, 7",
            // ...
            "9, Fizz",
            "10, Buzz",
            "11, 11",
            // ...
            "15, FizzBuzz",
            // ...
            "20, Buzz",
            // ...
            "30, FizzBuzz",
            // ...
            "90, FizzBuzz",
            "98, 98",
            "99, Fizz",
            "100, Buzz",
            "101, OutOfRangeException",
    })
    void converts_according_specs(int input, String expectedOutput) throws OutOfRangeException {
        if (OutOfRangeException.class.getSimpleName().equals(expectedOutput)) {
            assertThatThrownBy(() -> FizzBuzz.convert(input))
                    .isInstanceOf(OutOfRangeException.class);
        } else {
            assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
        }
    }

}