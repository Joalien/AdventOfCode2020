package fr.kubys;

public class Input {
    public int min;
    public int max;
    public char letter;
    public String string;

    public static Input fromString(String s) {
        Input input = new Input();
        String[] array = s.split(" ");
        input.min = Integer.parseInt(array[0].split("-")[0]);
        input.max = Integer.parseInt(array[0].split("-")[1]);
        input.letter = array[1].charAt(0);
        input.string = array[2];

        return input;
    }
}
