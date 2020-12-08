package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    final static Set<Character> allLowercaseLetters = convertToSetOfChar("azertyuiopqsdfghjklmwxcvbn");

    // input.replaceAll("\n\n", "-").replaceAll("\n", " ").replaceAll("-", "\n");
    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(Main::mapToSet)
                .map(sets -> sets.stream()
                                .collect(() -> new HashSet<>(allLowercaseLetters), AbstractCollection::retainAll, AbstractCollection::retainAll))
                .mapToInt(Set::size)
                .sum());
    }

    private static Set<Set<Character>> mapToSet(String string) {
        return Arrays.stream(string.split(" "))
                .map(Main::convertToSetOfChar)
                .collect(Collectors.toSet());
    }

    private static Set<Character> convertToSetOfChar(String chars) {
        return chars.chars().mapToObj(value -> (char) value).collect(Collectors.toSet());
    }
}
