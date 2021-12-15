package com.alcamech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


record LowPoint(int value, int risk, int x, int y){}

public class DayNine {
    String filePath = getClass().getClassLoader().getResource("advent-day-9-input.txt").getPath();
    private final int[][] heightMap = readInputIntoMatrix(filePath);

    public DayNine() throws IOException {
    }

    private static int[][] readInputIntoMatrix(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .map(line -> Arrays.stream(line.split(""))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .toArray(int[][]::new);
    }

    private static int sumLowPoints(List<LowPoint> lowPoints) {
        return  lowPoints.stream().mapToInt(LowPoint::risk).sum();
    }


    public int solutionPartOne() {
        List<LowPoint> lowPoints = new ArrayList<>();
        for(int i=0; i < heightMap.length; i++) {
            for(int j=0; j < heightMap.length; j++) {
                int location = heightMap[i][j];
                boolean lowest = true;
                System.out.println(location);
                if(j > 0) {
                    int left = heightMap[i][j-1];
                    if(location >= left) {
                        lowest = false;
                    }
                    System.out.println("LEFT: "+left);
                }
               if(j <= heightMap.length - 2) {
                   int right = heightMap[i][j+1];
                   if(location >= right) {
                       lowest = false;
                   }
                   System.out.println("RIGHT: "+right);
               }
               if(i <= heightMap.length -2) {
                   int bottom = heightMap[i+1][j];
                   if(location >= bottom) {
                       lowest = false;
                   }
                   System.out.println("BOTTOM: "+bottom);
               }
               if(i > 0) {
                   int top = heightMap[i-1][j];
                   if(location >= top) {
                       lowest = false;
                   }
                   System.out.println("UP: "+top);
               }

               if(lowest) {
                   System.out.println("LOWEST: "+location);
                   LowPoint lowPoint = new LowPoint(location,location+1,i,j);
                   lowPoints.add(lowPoint);
               }
            }
        }

        return sumLowPoints(lowPoints);
    }
}
