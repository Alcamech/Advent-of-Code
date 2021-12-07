package com.alcamech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.List;

record Coordinate(int x, int y){}

public class DayFive {
    private String file = getClass().getClassLoader().getResource("advent-day-5-input.txt").getPath();
    List<String> lines = Files.readAllLines(Path.of(file));

    public DayFive() throws IOException {
    }

    // Produces a list of coordinate pairs
    // example: [Coordinate[x=609, y=916]=Coordinate[x=60, y=367] ... Coordinate[x=481, y=628]=Coordinate[x=657, y=628]]
    public List<AbstractMap.SimpleImmutableEntry<Coordinate,Coordinate>> parseCoordinates(List<String> lines) {
        return lines.stream()
                .map(line -> new AbstractMap.SimpleImmutableEntry<>(
                        new Coordinate(Integer.parseInt(line.split(" -> ")[0].split(",")[0]),
                                Integer.parseInt(line.split(" -> ")[0].split(",")[1])),
                        new Coordinate(Integer.parseInt(line.split(" -> ")[1].split(",")[0]),
                                Integer.parseInt(line.split(" -> ")[1].split(",")[1])))).toList();
    }

    public int solutionPartOne(){
        List<AbstractMap.SimpleImmutableEntry<Coordinate,Coordinate>> coordinateList = parseCoordinates(lines);
        System.out.println(coordinateList);

        return 0;
    }
}
