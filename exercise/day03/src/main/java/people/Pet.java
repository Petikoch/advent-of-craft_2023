package people;

sealed public interface Pet {
    String name();

    int age();

    record Bird(String name, int age) implements Pet {
    }

    record Cat(String name, int age) implements Pet {
    }

    record Dog(String name, int age) implements Pet {
    }

    record Hamster(String name, int age) implements Pet {
    }

    record Snake(String name, int age) implements Pet {
    }

    record Turtle(String name, int age) implements Pet {
    }

}

