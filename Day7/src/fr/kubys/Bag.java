package fr.kubys;

import java.util.HashMap;
import java.util.Map;

public class Bag {
    private static final Map<String, Bag> allBags = new HashMap<>();
    public String name;
    public Map<Bag, Integer> content = new HashMap<>();

    private Bag() {
    }

    private static Bag getOrCreate(String name) {
        if (allBags.containsKey(name)) {
            return allBags.get(name);
        }

        Bag bag = new Bag();
        bag.name = name;
        allBags.put(name, bag);
        return bag;
    }

    public static void createBag(String input) {
        Bag bag = getOrCreate(input.split(" ")[0] + " " + input.split(" ")[1]);
        try {
            for (String contents : input.split(",")) {
                String[] s = contents.split(" ");
                bag.content.put(getOrCreate(s[s.length - 3] + " " + s[s.length - 2]), Integer.parseInt(s[s.length - 4]));
            }
        } catch (Exception ignored) {
            // This bag contains no other bags
        }
    }

    public static Map<String, Bag> getAllBags() {
        return allBags;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "name='" + name + '\'' +
                ", content=" + content +
                '}';
    }
}
