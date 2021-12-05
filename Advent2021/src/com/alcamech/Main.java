package com.alcamech;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("----- DAY ONE -----");
        DayOne dayOne = new DayOne();
        System.out.println(dayOne.solutionPartOne());
        System.out.println(dayOne.solutionPartTwo());
        System.out.println("----- DAY TWO -----");
        DayTwo dayTwo = new DayTwo();
        System.out.println(dayTwo.solutionPartOne());
        System.out.println(dayTwo.solutionPartTwo());
        System.out.println("----- DAY THREE -----");
        DayThree dayThree = new DayThree();
        System.out.println(dayThree.solutionPartOne());
        System.out.println(dayThree.solutionPartTwo());
        System.out.println("----- DAY FOUR -----");
        DayFour dayFour = new DayFour();
        System.out.println(dayFour.solutionPartOne());
    }
}
