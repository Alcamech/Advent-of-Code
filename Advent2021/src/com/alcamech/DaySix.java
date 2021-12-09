package com.alcamech;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DaySix {
    private String file = getClass().getClassLoader().getResource("advent-day-6-input.txt").getPath();
    List<Integer> schoolOfLanternfish = new ArrayList<>();


    public void handleInput() throws IOException {
        Path input = Paths.get(file);

        try(Scanner scanner = new Scanner(input)){
            scanner.useDelimiter("\\D");
            while(scanner.hasNextInt()){
                schoolOfLanternfish.add(scanner.nextInt());
            }
        }
    }

    public long solutionPartOne() throws IOException {
        long count = 0;
        handleInput();
        for(int day = 0; day < 80; day++) {
            for (int i=0; i < schoolOfLanternfish.size(); i++) {
                int lanternfish = schoolOfLanternfish.get(i);
                if (lanternfish != 0) {
                    lanternfish--;
                    schoolOfLanternfish.set(i,lanternfish);
                } else {
                    schoolOfLanternfish.set(i,6);
                    schoolOfLanternfish.add(9);
                }
            }
            count = schoolOfLanternfish.size();
        }
        return count;
    }

    public long solutionPartTwo() throws IOException {
        schoolOfLanternfish.clear();
        handleInput();

        long[] fish = new long[10];
        int days = 0;
        for (int i : schoolOfLanternfish) {
            fish[i]++;
        }
        while (days < 256) {
            fish[9] += fish[0];
            fish[7] += fish[0];
            fish[0] = 0;
            fish[0] = fish[1];
            fish[1] = fish[2];
            fish[2] = fish[3];
            fish[3] = fish[4];
            fish[4] = fish[5];
            fish[5] = fish[6];
            fish[6] = fish[7];
            fish[7] = fish[8];
            fish[8] = fish[9];
            fish[9] = 0;
            days++;
        }
        long total = 0;

        for (long l : fish) {
            total += l;
        }

        return total;
    }
}
