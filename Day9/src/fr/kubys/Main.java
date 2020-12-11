package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static final int preambleSize = 25;

    public static void main(String[] args) throws IOException {
        ArrayList<Long> allNumbers = (ArrayList<Long>) Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());

        long invalidNumber = findInvalidNumber(allNumbers);
        System.out.println(invalidNumber);

        List<Long> contiguousNumbers = findContiguousNumbers(allNumbers, invalidNumber);
        System.out.println(
                contiguousNumbers.stream()
                        .mapToLong(value -> value)
                        .min()
                        .orElseThrow() +
                contiguousNumbers.stream()
                        .mapToLong(value -> value)
                        .max()
                        .orElseThrow()
        );
    }

    private static List<Long> findContiguousNumbers(ArrayList<Long> allNumbers, long invalidNumber) {
        long currentSum = allNumbers.get(0) + allNumbers.get(1);
        int j = 1;
        for (int i = 0; i < allNumbers.indexOf(invalidNumber); i++) {
            if (currentSum < invalidNumber) {
                while (currentSum < invalidNumber) {
                    currentSum += allNumbers.get(++j);
                }
            } else if (currentSum > invalidNumber) {
                while (currentSum > invalidNumber) {
                    currentSum -= allNumbers.get(j--);
                }
            }
            if (currentSum == invalidNumber) {
                return IntStream.rangeClosed(i, j).mapToObj(allNumbers::get).collect(Collectors.toList());
            }
            currentSum -= allNumbers.get(i);
        }
        throw new NoSuchElementException();
    }

    private static long findInvalidNumber(ArrayList<Long> allNumbers) {
        List<Long> allSum = new LinkedList<>();
        for (int i = 0; i < preambleSize; i++) {
            for (int j = i + 1; j < preambleSize; j++) {
                allSum.add(allNumbers.get(i) + allNumbers.get(j));
            }
        }

        for (int i = preambleSize; i < allNumbers.size(); i++) {
            if (!allSum.contains(allNumbers.get(i))) {
                return allNumbers.get(i);
            }
            for (int j = i - preambleSize + 1; j < i; j++) {
                allSum.remove(allNumbers.get(j) + allNumbers.get(i - preambleSize));
                allSum.add(allNumbers.get(j) + allNumbers.get(i));
            }
        }
        throw new NoSuchElementException();
    }
}
