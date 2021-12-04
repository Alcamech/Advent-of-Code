package com.alcamech;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class DayThree {
    String filePath = getClass().getClassLoader().getResource("advent-day-3-input.txt").getPath();
    private final String[][] diagnosticReport = readInputIntoMatrix(filePath);
    private final int numOfColumns = diagnosticReport[0].length;

    public DayThree() throws IOException {
    }

    String[] getColumn(String[][] matrix, int column) {
        return Arrays.stream(matrix).map(binary -> binary[column]).toArray(String[]::new);
    }

    public int getCommonBit(String[] column) {
        long zeroCount = Arrays.stream(column).filter((s) -> s.equals("0")).count();
        long oneCount = Arrays.stream(column).filter((s) -> s.equals("1")).count();
        if(zeroCount == oneCount) {
            return 2;
        }
        return zeroCount > oneCount ? 0 : 1;
    }

    public static String[][] readInputIntoMatrix(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .map(line -> Arrays.stream(line.split(""))
                    .toArray(String[]::new))
                .toArray(String[][]::new);
    }

    public int solutionPartOne() {
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();

        for(int i=0; i<numOfColumns; i++) {
            String[] column = getColumn(diagnosticReport,i);
            int commonBit = getCommonBit(column);
            gamma.append(commonBit);
            int leastCommonBit = commonBit ^ 1;
            epsilon.append(leastCommonBit);
        }

        int gammaNum = Integer.parseInt(String.valueOf(gamma),2);
        int epsilonNum = Integer.parseInt(String.valueOf(epsilon),2);
        return gammaNum * epsilonNum;
    }

    public int findRating(String[][] diagnosticReport, boolean isO2GenRating) {
        int ratingNum;
        StringBuilder rating = new StringBuilder();
        String[][] tmpDiagnosticReport = diagnosticReport;

        for(int i=0; i<numOfColumns; i++) {
            String[] column = getColumn(tmpDiagnosticReport, i);
            int commonBit = getCommonBit(column);
            String keep;

            if(commonBit == 2 && isO2GenRating) {
                keep = String.valueOf(1);
            } else if (commonBit == 2) {
                keep = String.valueOf(0);
            } else if (isO2GenRating) {
                keep = String.valueOf(commonBit);
            } else {
                keep = String.valueOf(commonBit ^ 1);
            }

            int finalI = i;
            tmpDiagnosticReport = Arrays.stream(tmpDiagnosticReport)
                    .filter(row -> Objects.equals(row[finalI],keep))
                    .toArray(String[][]::new);

            if(tmpDiagnosticReport.length == 1) {
                rating = new StringBuilder(String.join("", tmpDiagnosticReport[0]));
            }
        }

        ratingNum = Integer.parseInt(String.valueOf(rating),2);
        return ratingNum;
    }

    public int solutionPartTwo() {
        int o2GenRating;
        int co2ScrubRating;

        o2GenRating = findRating(diagnosticReport, true);
        co2ScrubRating = findRating(diagnosticReport,false);

        return  o2GenRating * co2ScrubRating;
    }
}
