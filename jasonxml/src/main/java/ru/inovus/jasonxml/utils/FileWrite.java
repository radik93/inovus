package ru.inovus.jasonxml.utils;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


public class FileWriteImpl {

    private static final String fileURL = "out/" + "jsonf" + ".json";

    private static final File file= new File(fileURL);


    //максимальный размер файла в байтах который можно вывести на ЭФ
    private static final long allowableLengthFile = 10000;


    public static void writeFile(String temp,long lineNumber)
    {
        try {
            FileChannel channel=FileChannel.open(Paths.get(fileURL), StandardOpenOption.READ,StandardOpenOption.WRITE);
            ByteBuffer buff = ByteBuffer.wrap(temp.getBytes(StandardCharsets.UTF_8));
            channel.write(buff,lineNumber);
            channel.close();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
    }

    public static void addEmpty(){
        String emptyText;
        emptyText="                                           "
                +"                                            \n";
        try {
        FileWriter fw = new FileWriter(file, true);
        fw.write(emptyText, 0, emptyText.length());
        fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static long getSizeFile()
    {

        return file.length();
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
             //   String ls = System.getProperty("line.separator");
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                 //   stringBuilder.append(ls);
                }
                    return stringBuilder.toString();

            } catch (IOException e) {
                return "Error read file";
            }
        }
            return "File is very large \n " +
                    "File available for viewing "+file.getAbsolutePath();
    }
}
