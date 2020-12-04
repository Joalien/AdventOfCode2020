package fr.kubys;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    static Set<String> listOfValidEcl = Set.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
    static Predicate<Map<String, String>> validByr = passport -> passport.containsKey("byr") && isBetween(1920, passport.get("byr"), 2002);
    static Predicate<Map<String, String>> validIyr = passport -> passport.containsKey("iyr") && isBetween(2010, passport.get("iyr"), 2020);
    static Predicate<Map<String, String>> validEyr = passport -> passport.containsKey("eyr") && isBetween(2020, passport.get("eyr"), 2030);
    static Predicate<Map<String, String>> validHgt = passport -> passport.containsKey("hgt") && validateHeight(passport.get("hgt"));
    static Predicate<Map<String, String>> validHcl = passport -> passport.containsKey("hcl") && passport.get("hcl").matches("^#[0-9a-f]{6}$");
    static Predicate<Map<String, String>> validEcl = passport -> passport.containsKey("ecl") && listOfValidEcl.contains(passport.get("ecl"));
    static Predicate<Map<String, String>> validPid = passport -> passport.containsKey("pid") && passport.get("pid").matches("^[0-9]{9}$");
    static Predicate<Map<String, String>> validCid = passport -> passport.containsKey("cid");
    static Predicate<Map<String, String>> validValidPassport = validByr.and(validIyr)
            .and(validEyr)
            .and(validHgt)
            .and(validHcl)
            .and(validEcl)
            .and(validPid);

    public static void main(String[] args) throws IOException {
        System.out.println(Files.readAllLines(Path.of("./src/resources/input"))
                .stream()
                .map(string -> string.split(" ")) // ["key:value", "key2:value2", ... ]
                .map(Main::arrayToMap)
                .filter(validValidPassport)
                .count());
    }

    // Can be improved
    private static HashMap<String, String> arrayToMap(String[] array) {
        HashMap<String, String> hm = new HashMap<>();
        Arrays.stream(array)
                .map(pair -> Map.entry(pair.split(":")[0], pair.split(":")[1]))
                .collect(Collectors.toList())
                .forEach(entry -> hm.put(entry.getKey(), entry.getValue()));
        return hm;
    }

    private static boolean validateHeight(String hgt) {
        int heightLength = hgt.length();
        String heightValue = hgt.substring(0, heightLength - 2);
        String heightUnit = hgt.substring(heightLength - 2);

        return switch (heightUnit) {
            case "cm" -> isBetween(150, heightValue, 193);
            case "in" -> isBetween(59, heightValue, 76);
            default -> false;
        };
    }

    private static boolean isBetween(int min, String value, int max) {
        int intValue = Integer.parseInt(value);
        return min <= intValue && intValue <= max;
    }
}
