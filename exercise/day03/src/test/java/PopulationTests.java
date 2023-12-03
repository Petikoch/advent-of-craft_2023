import org.junit.jupiter.api.Test;
import people.Person;
import people.PetType;
import people.Population;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PopulationTests {

    private static final Population POPULATION = new Population(
            List.of(
                    new Person("Peter", "Griffin")
                            .addPet(PetType.CAT, "Tabby", 2),
                    new Person("Stewie", "Griffin")
                            .addPet(PetType.CAT, "Dolly", 3)
                            .addPet(PetType.DOG, "Brian", 9),
                    new Person("Joe", "Swanson")
                            .addPet(PetType.DOG, "Spike", 4),
                    new Person("Lois", "Griffin")
                            .addPet(PetType.SNAKE, "Serpy", 1),
                    new Person("Meg", "Griffin")
                            .addPet(PetType.BIRD, "Tweety", 1),
                    new Person("Chris", "Griffin")
                            .addPet(PetType.TURTLE, "Speedy", 4),
                    new Person("Cleveland", "Brown")
                            .addPet(PetType.HAMSTER, "Fuzzy", 1)
                            .addPet(PetType.HAMSTER, "Wuzzy", 2),
                    new Person("Glenn", "Quagmire")
            )
    );

    @Test
    void whoOwnsTheYoungestPet() {
        var filtered = POPULATION.personWithYoungestPet();

        assert filtered != null;
        assertThat(filtered.firstName()).isEqualTo("Lois");
    }
}
