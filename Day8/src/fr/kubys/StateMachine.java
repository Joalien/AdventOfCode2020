package fr.kubys;

import java.util.*;

public class StateMachine {
    public ArrayList<State> allStates = new ArrayList<>();
    public int acc = 0;
    Set<Integer> alreadyTraveledIndex = new HashSet<>();
    private boolean hasSwitch = false;
    private Integer failedIndex = null;

    public int findFailedIndex(int currentIndex) {
        if (currentIndex == allStates.size() - 1) return failedIndex;
        if (!alreadyTraveledIndex.add(currentIndex)) throw new IllegalStateException(String.valueOf(acc));
        acc += allStates.get(currentIndex).accumulateCurrentValue();
        try {
            return findFailedIndex(allStates.get(currentIndex).getNextIndex());
        } catch (IllegalStateException ise) {
            if (!hasSwitch) {
                allStates.get(currentIndex).switchCurrentState();
                hasSwitch = true;
                failedIndex = currentIndex;
                try {
                    return findFailedIndex(allStates.get(currentIndex).getNextIndex());
                } catch (IllegalStateException ise2) {
                    allStates.get(currentIndex).switchCurrentState();
                    hasSwitch = false;
                    throw ise2;
                }
            }
            else throw ise;
        }
    }

    public int travel(int currentIndex) {
        if (currentIndex == allStates.size() - 1) return acc;
        if (!alreadyTraveledIndex.add(currentIndex)) throw new IllegalStateException(String.valueOf(acc));
        acc += allStates.get(currentIndex).accumulateCurrentValue();
        return travel(allStates.get(currentIndex).getNextIndex());
    }

    public void addState(String input) {
        State state = new State();
        state.type = State.Type.valueOf(input.split(" ")[0]);
        state.value = Integer.parseInt(input.split(" ")[1]);
        state.currentIndex = this.allStates.size();

        this.allStates.add(state);
    }
}
