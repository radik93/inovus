package ru.inovus.stateregnumber.utilities;

public class GratePerformance {
    private static final String region  = "116";

    public static String getPerformance(int serA, int serB, int serC, int gosNum)
    {
       return GrateLetters.getLettersSeries(serA)
                +String.format("%03d", gosNum)
                +GrateLetters.getLettersSeries(serB)
                +GrateLetters.getLettersSeries(serC)
                +region;
    }
}
