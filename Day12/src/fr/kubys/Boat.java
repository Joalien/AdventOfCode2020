package fr.kubys;

public class Boat {
    int x_position;
    int y_position;
    Waypoint waypoint = new Waypoint();


    public void applyInstruction(Instruction instruction) {
        switch (instruction.letter) {
            case 'N' -> waypoint.relative_y_position += instruction.distance;
            case 'S' -> waypoint.relative_y_position -= instruction.distance;
            case 'E' -> waypoint.relative_x_position += instruction.distance;
            case 'W' -> waypoint.relative_x_position -= instruction.distance;
            case 'F' -> {
                x_position += waypoint.relative_x_position * instruction.distance;
                y_position += waypoint.relative_y_position * instruction.distance;
            }
            case 'L' -> rotate(instruction.distance);
            case 'R' -> rotate(360 - instruction.distance);
            default -> throw new IllegalArgumentException();
        }
        System.out.println(this);
    }

    private void rotate(int angle) {
        switch (angle) {
            case 90 -> {
                int oldX = waypoint.relative_x_position;
                waypoint.relative_x_position = -waypoint.relative_y_position;
                waypoint.relative_y_position = oldX;
            }
            case 180 -> {
                waypoint.relative_x_position = -waypoint.relative_x_position;
                waypoint.relative_y_position = -waypoint.relative_y_position;
            }
            case 270 -> {
                int oldX = waypoint.relative_x_position;
                waypoint.relative_x_position = waypoint.relative_y_position;
                waypoint.relative_y_position = -oldX;
            }
            default -> throw new IllegalArgumentException();
        }
    }

    private class Waypoint {
        int relative_x_position = 10;
        int relative_y_position = 1;

        @Override
        public String toString() {
            return "Waypoint{" +
                    "relative_x_position=" + relative_x_position +
                    ", relative_y_position=" + relative_y_position +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Boat{" +
                "x_position=" + x_position +
                ", y_position=" + y_position +
                ", waypoint=" + waypoint +
                '}';
    }
}