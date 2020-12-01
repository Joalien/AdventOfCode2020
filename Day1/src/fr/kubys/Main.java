package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        List<Integer> input = Files.readAllLines(Path.of("./src/resources/input1"))
                .stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        input.forEach(i ->
                input.stream()
                .filter(j -> !i.equals(j))
                .filter(j -> i + j == 2020)
                .findFirst()
                .ifPresent(j -> System.out.println(i * j))
        );

        input.forEach(i ->
            input.forEach(j ->
                input.stream()
                        .filter(k -> i + j + k == 2020)
                        .findFirst()
                        .ifPresent(k -> System.out.println(i * j * k))
            )
        );

    }
}
