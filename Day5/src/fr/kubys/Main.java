package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Integer> allIds = Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(Main::convertToBinaryString)
                .map(string -> Map.entry(Integer.parseInt(string.substring(0, 7), 2), Integer.parseInt(string.substring(7), 2)))
                .map(entry -> entry.getKey() * 8 + entry.getValue())
                .collect(Collectors.toList());

        int min = allIds.stream().mapToInt(Integer::intValue).min().orElseThrow();
        int max = allIds.stream().mapToInt(Integer::intValue).max().orElseThrow();

        int expectedValue = (max * (max + 1) - (min - 1) * min) / 2;
        int actualValue = allIds.stream().mapToInt(Integer::intValue).sum();

        System.out.println(expectedValue - actualValue);
    }

    private static String convertToBinaryString(String string) {
        return string.replaceAll("B", "1")
                .replaceAll("F", "0")
                .replaceAll("R", "1")
                .replaceAll("L", "0");
    }

}
