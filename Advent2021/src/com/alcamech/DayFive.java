package com.alcamech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

record Coordinate(int x, int y){}

public class DayFive {
    private String file = getClass().getClassLoader().getResource("advent-day-5-input.txt").getPath();
    List<String> lines = Files.readAllLines(Path.of(file));
    Map<Map<Integer,Integer>,Integer> coordinateCount = new HashMap<>();

    public DayFive() throws IOException {
    }

    public void plotCoordinateCount(AbstractMap.SimpleImmutableEntry<Coordinate, Coordinate> coordPair) {
        int x1 = coordPair.getKey().x();
        int y1 = coordPair.getKey().y();
        int x2 = coordPair.getValue().x();
        int y2 = coordPair.getValue().y();

        if(y1 == y2) {
            if(x1 < x2) {
                for(int x = x1; x <= x2; ++x) {
                    Map<Integer,Integer> coord =  new HashMap<>();
                    coord.put(x,y1);
                    int count = coordinateCount.getOrDefault(coord, 0);
                    coordinateCount.put(coord, count + 1);
                }
            } else if (x1 > x2) {
                for(int x = x1; x >= x2; --x) {
                    Map<Integer,Integer> coord =  new HashMap<>();
                    coord.put(x,y1);
                    int count = coordinateCount.getOrDefault(coord, 0);
                    coordinateCount.put(coord, count + 1);
                }
            }
        }

        if(x1 == x2) {
            if(y1 < y2) {
                for(int y = y1; y <= y2; ++y) {
                    Map<Integer,Integer> coord =  new HashMap<>();
                    coord.put(x1,y);
                    int count = coordinateCount.getOrDefault(coord, 0);
                    coordinateCount.put(coord, count + 1);
                }
            } else if (y1 > y2){
                for(int y = y1; y >= y2; --y) {
                    Map<Integer,Integer> coord =  new HashMap<>();
                    coord.put(x1,y);
                    int count = coordinateCount.getOrDefault(coord, 0);
                    coordinateCount.put(coord, count + 1);
                }
            }
        }
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

    public long solutionPartOne(){
        List<AbstractMap.SimpleImmutableEntry<Coordinate,Coordinate>> coordinateList = parseCoordinates(lines);
        for (AbstractMap.SimpleImmutableEntry<Coordinate, Coordinate> coordPair : coordinateList) {
            if (coordPair.getKey().x() == coordPair.getValue().x() || coordPair.getKey().y() == coordPair.getValue().y()) {
                plotCoordinateCount(coordPair);
            }
        }

        return coordinateCount.entrySet().stream().filter(x -> x.getValue() > 1 ).count();
    }
}
