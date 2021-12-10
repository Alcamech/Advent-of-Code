package com.alcamech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DayEight {
    private String file = getClass().getClassLoader().getResource("advent-day-8-input.txt").getPath();
    List<String> lines = Files.readAllLines(Path.of(file));

    public DayEight() throws IOException {
    }

    public int solutionPartOne() {
        int count = 0;
        for(String line: lines) {
            String[] digits = line.split("\\|")[1].trim().split(" ");
            for(String digit: digits) {
                switch (digit.length()) {
                    case 2:
                    case 3:
                    case 4:
                    case 7:
                        count++;
                        break;
                }
            }
        }
        return count;
    }
    static final Map<Integer, Integer> codeToDigits = Map.of(42,0, 17,1, 34,2, 39,3, 30,4, 37,5, 41,6, 25,7, 49,8, 45,9);

    static int decode(String patterns, String digits){
        Map<Character, Integer> frequencies = new HashMap<>();
        patterns.trim().replaceAll("\\s+", "").chars().forEach(c -> frequencies.merge((char) c, 1, (a, b) -> a + b));

        return Arrays.stream(digits.trim().split(" "))
                .mapToInt(digit -> digit.chars().map(c -> frequencies.get((char) c)).sum())
                .map(codeToDigits::get)
                .reduce(0, (n, d) -> (10 * n) + d);
    }

    public int solutionPartTwo(){
        int sum = 0;

        for(String line : lines){
            String[] patternsAndDigits = line.split("\\|");

            int outputValue = decode(patternsAndDigits[0], patternsAndDigits[1]);
            sum += outputValue;
        }

       return sum;
    }
}
