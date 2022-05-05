package ru.inovus.jasonxml.utils;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class FileWrite {

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

    public static List textOut() {
        List<String> lines = null;
        if (file.length() < allowableLengthFile) {
            try {

                lines = new ArrayList<>(Files.readAllLines(Paths.get(fileURL)));
            //  lines.replaceAll(s->s.replace(" ","&nbsp;"));
                }
            catch (IOException e) {

                lines.add("Error read file");
                }
            }
         else {
            lines.add("File is very large");
            lines.add("File available for viewing "
                    + file.getAbsolutePath());
              }
       return lines;
    }
}
