package fr.kubys;

public class State {
    Type type;
    int currentIndex;
    int value;

    public int getNextIndex() {
        return switch (type) {
            case nop, acc -> currentIndex + 1;
            case jmp -> currentIndex + value;
        };
    }

    public int accumulateCurrentValue() {
        return switch (type) {
            case acc -> value;
            case nop, jmp -> 0;
        };
    }

    public void switchCurrentState() {
        switch (type) {
            case jmp -> this.type = Type.nop;
            case nop -> this.type = Type.jmp;
        };
    }

    enum Type {
        jmp,
        acc,
        nop;
    }

    @Override
    public String toString() {
        return "State{" +
                "type=" + type +
                ", currentIndex=" + currentIndex +
                ", value=" + value +
                '}';
    }
}