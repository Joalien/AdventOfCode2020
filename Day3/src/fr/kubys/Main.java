package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException {
        Cylinder cylinder = createCylinder();

        Set<Slope> slopes = Set.of(
                new Slope(3, 1),
                new Slope(1, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2)
        );

        System.out.println(
                slopes.stream()
                        .mapToLong(slope -> getNumberOfCollision(cylinder, slope))
                        .reduce(1, (a, b) -> a * b)
        );
    }

    private static Cylinder createCylinder() throws IOException {
        int height = Files.readAllLines(Path.of("./src/resources/input"))
               .size();
        int width = Files.readAllLines(Path.of("./src/resources/input"))
               .stream().findAny().get().length();

        Cylinder cylinder = new Cylinder(height, width);

        int lineNumber = 0;
        for (String line : Files.readAllLines(Path.of("./src/resources/input"))) {
            for (int i = 0, charArrayLength = line.toCharArray().length; i < charArrayLength; i++) {
                cylinder.setBoxContent(i, lineNumber, line.charAt(i) != '#');
            }
            lineNumber++;
        }
        return cylinder;
    }

    private static long getNumberOfCollision(Cylinder cylinder, Slope slope) {
        return IntStream.range(0, (cylinder.height / slope.y))
                .mapToObj(yPosition -> cylinder.getBoxContent(slope.x * yPosition, slope.y * yPosition))
                .filter(b -> !b)
                .count();
    }
}
