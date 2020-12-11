package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        StateMachine stateMachine = initStateMachine();
        int failedIndex = stateMachine.findFailedIndex(0);
        System.out.println("Failed index : " + failedIndex);

        stateMachine = initStateMachine();
        stateMachine.allStates.get(failedIndex).switchCurrentState();
        System.out.println(stateMachine.travel(0));
    }

    private static StateMachine initStateMachine() throws IOException {
        StateMachine stateMachine = new StateMachine();
        Files.readAllLines(Path.of("./src/resources/input"))
                .forEach(stateMachine::addState);
        return stateMachine;
    }
}
