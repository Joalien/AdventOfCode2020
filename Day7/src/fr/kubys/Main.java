package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        Files.readAllLines(Path.of("./src/resources/input")).forEach(Bag::createBag);

        Bag shinyGoldBag = Bag.getAllBags().get("shiny gold");

        System.out.println(getAllAncestors(shinyGoldBag).size());
        System.out.println(computeNumberOfBag(shinyGoldBag) - 1);
    }

    private static Set<Bag> getAllAncestors(Bag shinyGoldBag) {
        Set<Bag> allAncestors = new HashSet<>();
        Set<Bag> currentAncestors = getCurrentAncestors(shinyGoldBag);

        while (!currentAncestors.isEmpty()) {
            allAncestors.addAll(currentAncestors);
            currentAncestors = currentAncestors.stream()
                    .map(Main::getCurrentAncestors)
                    .flatMap(Set::stream)
                    .collect(Collectors.toSet());
        }
        return allAncestors;
    }

    private static Set<Bag> getCurrentAncestors(Bag bag) {
        return Bag.getAllBags().values()
                .stream()
                .filter(bag1 -> bag1.content.containsKey(bag))
                .collect(Collectors.toSet());
    }

    private static int computeNumberOfBag(Bag bag) {
        final int itself = 1;
        final int containedBags = bag.content.entrySet()
                .stream()
                .mapToInt(entry -> computeNumberOfBag(entry.getKey()) * entry.getValue())
                .sum();
        return itself + containedBags;
    }
}
