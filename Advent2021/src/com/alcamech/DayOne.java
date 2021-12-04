package com.alcamech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayOne {
    String file = getClass().getClassLoader().getResource("advent-day-1-input.txt").getFile();

    public static List<Integer> createNumListFromFile(String file) {
        List<Integer> numList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                numList.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numList;
    }

    public int solutionPartOne() {
        List<Integer> numList = createNumListFromFile(file);
        int answer = 0;
        int prevDepth = numList.get(0);

        for (Integer integer : numList) {
            if (integer > prevDepth) {
                answer++;
            }
            prevDepth = integer;
        }
        return answer;
    }

    public int solutionPartTwo() {
        int answer = 0;
        List<Integer> numList = createNumListFromFile(file);
        int prevCalc = numList.get(0) + numList.get(1) + numList.get(2);
        for(int i=0; i<numList.size()-2; i++) {
            int sum = numList.get(i) + numList.get(i+1) + numList.get(i+2);

            if(sum > prevCalc) {
                answer++;
            }

            prevCalc = sum;
        }

        return answer;
    }
}