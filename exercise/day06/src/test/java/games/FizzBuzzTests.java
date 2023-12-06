package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FizzBuzzTests {

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "4, 4",
            "7, 7",
            "98, 98",
    })
    void numbers(int input, String expectedOutput) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
            "3, Fizz",
            "6, Fizz",
            "9, Fizz",
            "99, Fizz",
    })
    void fizzes(int input, String expectedOutput) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
            "5, Buzz",
            "10, Buzz",
            "20, Buzz",
            "100, Buzz",
    })
    void buzzes(int input, String expectedOutput) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
            "15, FizzBuzz",
            "30, FizzBuzz",
            "90, FizzBuzz",
    })
    void fizzBuzzes(int input, String expectedOutput) throws OutOfRangeException {
        assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({
            "-1",
            "0",
            "101",
    })
    void failsWhenOutOfRange(int input) throws OutOfRangeException {
        assertThatThrownBy(() -> FizzBuzz.convert(0))
                .isInstanceOf(OutOfRangeException.class);
    }
}