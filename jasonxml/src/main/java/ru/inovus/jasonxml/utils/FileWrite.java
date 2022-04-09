package ru.inovus.jasonxml.utils;


import java.io.*;

public class FileWrite {

    private static final String fileURL = "out/" + "jsonf" + ".json";

    private static final File file= new File(fileURL);

    //максимальный размер файла в байтах который можно вывести на ЭФ
    private static final long allowableLengthFile = 10000;

    public static void writeJSONFile(String temp) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(temp, 0, temp.length());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanFile() {
        try {
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeErr(String errText) {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(errText, 0, errText.length());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String textOut() {

        if (file.length() < allowableLengthFile) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileURL));
                String line = null;
                StringBuilder stringBuilder = new StringBuilder();
                String ls = System.getProperty("line.separator");
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                    return stringBuilder.toString();
                }
            } catch (IOException e) {
                return "Error read file";
            }
        }
            return "File is very large \n " +
                    "File available for viewing "+file.getAbsolutePath();
    }
}
