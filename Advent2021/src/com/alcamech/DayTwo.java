package com.alcamech;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayTwo {
    String file = getClass().getClassLoader().getResource("advent-day-2-input.txt").getFile();

    public int solutionPartOne() {
        int forwardPos = 0;
        int depth = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] commands = line.split("\\s+");
                String command = commands[0];
                int unit = Integer.parseInt(commands[1]);
                switch(command) {
                    case "forward":
                        forwardPos+=unit;
                        break;
                    case "up":
                        depth-=unit;
                        break;
                    case "down":
                        depth+=unit;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return forwardPos * depth;
    }

    public int solutionPartTwo() {
        int forwardPos = 0;
        int depth = 0;
        int aim = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] commands = line.split("\\s+");
                String command = commands[0];
                int unit = Integer.parseInt(commands[1]);
                switch(command) {
                    case "forward":
                        forwardPos+=unit;
                        depth+=aim*unit;
                        break;
                    case "up":
                        aim-=unit;
                        break;
                    case "down":
                        aim+=unit;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return forwardPos * depth;
    }

}
