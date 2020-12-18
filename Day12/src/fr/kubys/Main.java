package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Boat boat = new Boat();
        Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(string -> new Instruction(string.charAt(0), Integer.parseInt(string.substring(1))))
                .forEach(boat::applyInstruction);

        System.out.println(Math.abs(boat.x_position) + Math.abs(boat.y_position));

    }

}
