package fr.kubys;


import java.util.Arrays;

public class BoardingArea {
    Seat[][] seats;

    public BoardingArea(int height, int width) {
        this.seats = new Seat[height][width];
    }

    public boolean getNextGeneration() {
        Seat[][] nextBoardingArea = new Seat[seats.length][seats[0].length];
        for (int height = 0; height < seats.length; height++) {
            for (int width = 0; width < seats[0].length; width++) {
                int getNumberOfOccupiedNeighbour = getNumberOfOccupiedNeighbour(height, width);
                if (this.seats[height][width] == Seat.NO_SEAT) nextBoardingArea[height][width] = Seat.NO_SEAT;
                else if (getNumberOfOccupiedNeighbour == 0) nextBoardingArea[height][width] = Seat.OCCUPIED;
                else if (getNumberOfOccupiedNeighbour >= 5) nextBoardingArea[height][width] = Seat.EMPTY;
                else nextBoardingArea[height][width] = this.seats[height][width];
            }
        }

        if (Arrays.deepEquals(this.seats, nextBoardingArea)) return true;

        this.seats = nextBoardingArea;
        return false;
    }

    private int getNumberOfOccupiedNeighbour(int height, int width) {
        int numberOfNeighbour = 0;

        for (Direction direction : Direction.values()) {
            int i = 1;
            while (isInside(height + direction.y_direction * i, width + direction.x_direction * i)) {
                Seat seat = this.seats[height + direction.y_direction * i][width + direction.x_direction * i];
                if (seat == Seat.OCCUPIED) {
                    numberOfNeighbour++;
                    break;
                } else if (seat == Seat.EMPTY) {
                    break;
                }
                i++;
            }
        }

        return numberOfNeighbour;
    }

    private boolean isInside(int height, int width) {
        return 0 <= height && height < this.seats.length
                && 0 <= width && width < this.seats[0].length;
    }

    public int getNumberOfOccupiedSeats() {
        int numberOfOccupiedSeat = 0;
        for (Seat[] seats : seats) {
            for (Seat seat : seats) {
                numberOfOccupiedSeat += seat == Seat.OCCUPIED ? 1 : 0;
            }
        }
        return numberOfOccupiedSeat;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Seat[] seats : seats) {
            for (Seat seat : seats) {
                board.append(seat.representation);
            }
            board.append("\n");
        }
        return board.toString();
    }

    enum Seat {
        OCCUPIED('#'),
        NO_SEAT('.'),
        EMPTY('L');

        char representation;

        Seat(char representation) {
            this.representation = representation;
        }

        public static Seat findByRep(char rep) {
            return Arrays.stream(values())
                    .filter(value -> value.representation == rep)
                    .findFirst()
                    .orElseThrow();
        }
    }

    private enum Direction {
        LEFT_UP(-1, -1),
        LEFT(0, -1),
        LEFT_DOWN(1, -1),
        UP(-1, 0),
        DOWN(1, 0),
        RIGHT_UP(-1, 1),
        RIGHT(0, 1),
        RIGHT_DOWN(1, 1);

        int y_direction, x_direction;

        Direction(int y_direction, int x_direction) {
            this.y_direction = y_direction;
            this.x_direction = x_direction;
        }
    }
}
