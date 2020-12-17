package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        BoardingArea boardingArea = createBoardingArea();
        while (!boardingArea.getNextGeneration()) {}

        System.out.println(boardingArea.getNumberOfOccupiedSeats());

    }

    private static BoardingArea createBoardingArea() throws IOException {
        int height = Files.readAllLines(Path.of("./src/resources/input"))
                .size();
        int width = Files.readAllLines(Path.of("./src/resources/input"))
                .stream().findAny().get().length();

        BoardingArea boardingArea = new BoardingArea(height, width);

        int lineNumber = 0;
        for (String line : Files.readAllLines(Path.of("./src/resources/input"))) {
            for (int i = 0, charArrayLength = line.toCharArray().length; i < charArrayLength; i++) {
                boardingArea.seats[lineNumber][i] = BoardingArea.Seat.findByRep(line.charAt(i));
            }
            lineNumber++;
        }
        return boardingArea;
    }
}
