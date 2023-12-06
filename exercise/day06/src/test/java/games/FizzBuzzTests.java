package games;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

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
            // ...
            "98, 98",
            "99, Fizz",
            "100, Buzz",
            "101, OutOfRangeException",
    })
    void stringbased_specs(int input, String expectedOutput) throws OutOfRangeException {
        if (OutOfRangeException.class.getSimpleName().equals(expectedOutput)) {
            assertThatThrownBy(() -> FizzBuzz.convert(input))
                    .isInstanceOf(OutOfRangeException.class);
        } else {
            assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
        }
    }

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void typesafe_specs(int input, String expectedOutput, Class<?> expectedExceptionClass) throws OutOfRangeException {
        if (expectedExceptionClass != null) {
            assertThatThrownBy(() -> FizzBuzz.convert(input))
                    .isInstanceOf(expectedExceptionClass);
        } else {
            assertThat(FizzBuzz.convert(input)).isEqualTo(expectedOutput);
        }
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                arguments(-1, null, OutOfRangeException.class),
                arguments(0, null, OutOfRangeException.class),
                arguments(1, "1", null),
                arguments(2, "2", null),
                arguments(3, "Fizz", null),
                arguments(4, "4", null),
                arguments(5, "Buzz", null),
                arguments(6, "Fizz", null),
                arguments(7, "7", null),
                // ...
                arguments(9, "Fizz", null),
                arguments(10, "Buzz", null),
                arguments(11, "11", null),
                // ...
                arguments(15, "FizzBuzz", null),
                // ...
                arguments(20, "Buzz", null),
                // ...
                arguments(30, "FizzBuzz", null),
                // ...
                arguments(90, "FizzBuzz", null),
                // ...
                arguments(98, "98", null),
                arguments(99, "Fizz", null),
                arguments(100, "Buzz", null),
                arguments(101, null, OutOfRangeException.class)
        );
    }

}