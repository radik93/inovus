package ru.inovus.stateregnumber.utilities;

public class GrateLetters {
    private static final String[] lettersSeries  = {"А", "Е", "Т", "О", "Р", "Н", "У", "К", "Х", "С", "В", "М"};

    public static String getLettersSeries(int index) {
        return lettersSeries[index];
    }

    public static int lettersSeriesSize() {
        return lettersSeries.length-1;
    }
}
