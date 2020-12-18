package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = Files.readString(Path.of("./src/resources/input"));

        final int departureTime = Integer.parseInt(input.split("\n")[0]);
        System.out.println(Arrays.stream(input.split("\n")[1].split(","))
                .filter(string -> !string.equals("x"))
                .mapToInt(Integer::parseInt)
                .mapToObj(busId -> Map.entry(busId, busId - (departureTime % busId)))
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .mapToInt(entry -> entry.getKey() * entry.getValue())
                .findFirst().orElseThrow());
    }

}
