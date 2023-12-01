import food.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

import static java.time.LocalDate.of;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

class FoodTests {
    private static final LocalDate expirationDate = of(2023, 12, 1);
    private static final UUID inspector = randomUUID();
    private static final LocalDate notFreshDate = expirationDate.plusDays(7);
    private static final LocalDate freshDate = expirationDate.minusDays(7);

    public static Stream<Arguments> notEdibleFood() {
        return Stream.of(
                Arguments.of(true, null, freshDate),
                Arguments.of(false, null, notFreshDate),
                Arguments.of(false, null, freshDate)
        );
    }

    @ParameterizedTest
    @MethodSource("notEdibleFood")
    void not_edible_if_not_fresh(boolean approvedForConsumption, UUID inspectorId, LocalDate now) {
        var food = new Food(
                expirationDate,
                approvedForConsumption,
                inspectorId);

        assertThat(food.isEdible(() -> now)).isFalse();
    }

    @Test
    void isEdible_happycase_true() {
        var food = new Food(
                expirationDate,
                true,
                inspector);

        assertThat(food.isEdible(() -> freshDate)).isTrue();
    }

    @Test
    void isEdible_dateExpired_false() {
        var food = new Food(
                expirationDate,
                true,
                inspector);

        assertThat(food.isEdible(() -> notFreshDate)).isFalse();
    }

    @Test
    void isEdible_notApprovedForConsumption_false() {
        var food = new Food(
                expirationDate,
                false,
                inspector);

        assertThat(food.isEdible(() -> freshDate)).isFalse();
    }
}