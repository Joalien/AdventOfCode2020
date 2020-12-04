package fr.kubys;

import java.util.Arrays;

public class Cylinder {
    int height, width;
    Boolean[][] boxes;

    public Cylinder(int height, int width) {
        this.height = height;
        this.width = width;
        boxes = new Boolean[height][width];
    }

    public Boolean getBoxContent(int width, int height) {
        return boxes[height][width % this.width];
    }

    public void setBoxContent(int width, int height, Boolean content) {
        boxes[height][width] = content;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (Boolean[] boolArrays : boxes) {
            for (Boolean bool : boolArrays) {
                board.append(bool ? '.' : '#');
            }
            board.append("\n");
        }
        return board.toString();
    }
}
