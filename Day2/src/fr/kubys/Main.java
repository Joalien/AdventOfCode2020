package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;

public class Main {

    static Predicate<Input> numberOfLetterPolicy = input -> {
        long numberOfOccurrences = input.string.chars().filter(intCode ->  intCode == (int) input.letter).count();
        return input.min <= numberOfOccurrences && numberOfOccurrences <= input.max;
    };

    static Predicate<Input> positionInPasswordPolicy = input -> {
        char firstChar = input.string.charAt(input.min - 1);
        char secondChar = input.string.charAt(input.max - 1);

        return (firstChar == input.letter || secondChar == input.letter)
                && firstChar != secondChar;
    };

    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(Input::fromString)
                .filter(positionInPasswordPolicy)
                .count());
    }
}

