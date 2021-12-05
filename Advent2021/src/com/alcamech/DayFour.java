package com.alcamech;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

public class DayFour {
    private String file = getClass().getClassLoader().getResource("advent-day-4-input.txt").getPath();
    List<String> lines = Files.readAllLines(Path.of(file));
    List<Integer> draws = Stream.of(lines.get(0).split(",")).map(Integer::parseInt).toList();
    List<String> boardInput = lines.subList(2,lines.size());
    public DayFour() throws IOException {
    }

    List<String> getColumn(List<List<String>> matrix, int column) {
        return matrix.stream().map(num -> num.get(column)).toList();
    }

    List<String> getRow(List<List<String>> matrix, int row) {
        return matrix.get(row);
    }

    public boolean isWinningBoard(List<List<String>> matrix) {
        boolean isRowEqual;
        boolean isColEqual;

        for(int i=0; i < matrix.size(); i++) {
            List<String> col = getColumn(matrix, i);
            List<String> row = getRow(matrix, i);
            isRowEqual = row.stream().allMatch(e -> e.equals("-1"));
            isColEqual = col.stream().allMatch(e -> e.equals("-1"));
            if (isRowEqual) {return true;}
            if(isColEqual) {return true;}
        }
        return false;
    }

    public int sumWinningBoard(List<List<String>> matrix) {
        return matrix.stream().flatMap(List::stream)
                    .filter(num -> !num.equals("-1"))
                    .mapToInt(Integer::parseInt).sum();
    }

    public int solutionPartOne() throws IOException {
        List<List<List<String>>> boards = new ArrayList<>();
        boolean winner;
        for(int i=0; i <boardInput.size(); i+=6) {
            List<List<String>> matrix = new ArrayList<>();
            for(int j=0; j<5; j++) {
                List<String> s = Arrays.asList(boardInput.get(i + j).trim().split("\\s+"));
                matrix.add(s);
            }
            boards.add(matrix);
        }

        for (Integer draw : draws) {
            for (List<List<String>> matrix : boards) {
                for (List<String> r : matrix) {
                    for (int c = 0; c < r.size(); c++) {
                        String position = r.get(c);
                        if (Integer.parseInt(position) == draw) {
                            r.set(c, "-1");
                            winner = isWinningBoard(matrix);
                            if(winner) {
                                return sumWinningBoard(matrix) * draw;
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    public int solutionPartTwo() {
        List<List<List<String>>> boards = new ArrayList<>();
        boolean winner;
        int winningBoardCount = 0;

        for(int i=0; i <boardInput.size(); i+=6) {
            List<List<String>> matrix = new ArrayList<>();
            for(int j=0; j<5; j++) {
                List<String> s = Arrays.asList(boardInput.get(i + j).trim().split("\\s+"));
                matrix.add(s);
            }
            boards.add(matrix);
        }

        for (Integer draw : draws) {
            for (List<List<String>> matrix : boards) {
                for (List<String> r : matrix) {
                    for (int c = 0; c < r.size(); c++) {
                        String position = r.get(c);
                        if (Integer.parseInt(position) == draw) {
                            r.set(c, "-1");
                            winner = isWinningBoard(matrix);
                            if(winner) {
                                winningBoardCount++;
                                if(winningBoardCount == boards.size()) {
                                    System.out.println("board: "+matrix);
                                    System.out.println("draw: "+draw);
                                    return sumWinningBoard(matrix) * draw;
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
