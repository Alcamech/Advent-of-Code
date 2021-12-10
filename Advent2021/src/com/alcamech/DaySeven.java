package com.alcamech;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DaySeven {
    private String file = getClass().getClassLoader().getResource("advent-day-7-input.txt").getPath();
    List<Integer> krabs = new ArrayList<>();

    private void handleInput() throws IOException {
        Path input = Paths.get(file);

        try(Scanner scanner = new Scanner(input)){
            scanner.useDelimiter("\\D");
            while(scanner.hasNextInt()){
                krabs.add(scanner.nextInt());
            }
        }
    }

    public int solutionPartOne() throws IOException {
        handleInput();
        Collections.sort(krabs);

        int median = krabs.get(krabs.size()/2);
        int cost = 0;

        for (Integer krabPos : krabs) {
            cost += Math.abs(krabPos - median);
        }
        return cost;
    }

    public long solutionPartTwo() throws IOException {
        krabs.clear();
        handleInput();
        Collections.sort(krabs);

        int average = (int) krabs.stream().mapToInt(i -> i).average().orElse(0.0);
        long cost = 0;

        for (Integer krabPos : krabs) {
           long delta = Math.abs(krabPos - average);
           cost += (delta * ( delta + 1))/2;
        }
        return cost;
    }
}
