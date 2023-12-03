import org.junit.jupiter.api.Test;
import people.*;
import people.Pet.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PopulationTests {

    @Test
    void whoOwnsTheYoungestPet() {
        Person lois = new Person("Lois", "Griffin", List.of(
                new Pet.Snake("Serpy", 1)));
        Population testPopulation = new Population(
                List.of(
                        new Person("Peter", "Griffin", List.of(
                                new Cat("Tabby", 2))),
                        new Person("Stewie", "Griffin", List.of(
                                new Cat("Dolly", 3),
                                new Dog("Brian", 9))),
                        new Person("Joe", "Swanson", List.of(
                                new Dog("Spike", 4))),
                        lois,
                        new Person("Meg", "Griffin", List.of(
                                new Bird("Tweety", 1))),
                        new Person("Chris", "Griffin", List.of(
                                new Turtle("Speedy", 4))),
                        new Person("Cleveland", "Brown", List.of(
                                new Hamster("Fuzzy", 1),
                                new Hamster("Wuzzy", 2))),
                        new Person("Glenn", "Quagmire")
                )
        );

        var actual = testPopulation.personWithYoungestPet();

        assert actual != null;
        assertThat(actual).isEqualTo(lois);
    }
}
