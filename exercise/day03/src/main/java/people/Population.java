package people;

import java.util.Comparator;
import java.util.List;

public record Population(List<Person> persons) {

    private static final Comparator<Person> BY_YOUNGEST_PET =
            Comparator.comparingInt(person -> person.pets().stream()
                    .mapToInt(Pet::age)
                    .min()
                    .orElse(Integer.MAX_VALUE));

    //TODO returns just the first of potentially many
    public Person personWithYoungestPet() {
        return persons().stream()
                .min(BY_YOUNGEST_PET)
                .orElse(null);
    }

}
