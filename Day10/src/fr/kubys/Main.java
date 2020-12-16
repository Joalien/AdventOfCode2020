package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static final Map<Queue<Integer>, Long> memoization = new HashMap<>();

    public static void main(String[] args) throws IOException {
        List<Integer> allNumbers = Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());

        System.out.println(countNumberOfJump(allNumbers));

        LinkedList<Integer> input = new LinkedList<>(allNumbers);
        input.offerFirst(0);
        System.out.println(getNumberOfDistinctWay(input));
    }

    private static long getNumberOfDistinctWay(Queue<Integer> input) {
        if (input.size() == 1) {
            return 1;
        }
        if (memoization.containsKey(input)) return memoization.get(input);
        Queue<Integer> originalQueue = new LinkedList<>(input);

        long sumToReturn = 0;
        int min = input.remove();

        for (int i = 1; i <= 3; i++) {
            if (input.element() - min == i) {
                sumToReturn += getNumberOfDistinctWay(new LinkedList<>(input));
                input.remove();
                if (input.peek() == null) break;
            }
        }

        memoization.put(originalQueue, sumToReturn);
        return sumToReturn;
    }

    private static int countNumberOfJump(List<Integer> allNumbers) {
        int stepOfOneCounter = 0;
        int stepOfTreeCounter = 0;
        int last = 0;

        for (Integer i : allNumbers) {
            if (i - last == 1) stepOfOneCounter++;
            else if (i - last == 3) stepOfTreeCounter++;
            else throw new IllegalStateException();
            last = i;
        }

        return stepOfOneCounter * (stepOfTreeCounter + 1);
    }
}
