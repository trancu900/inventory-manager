package vn.tvc.inventory.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    public static <T> void write(String path, List<T> items) {
        try {
            PrintWriter printWriter = new PrintWriter(path);
            for (Object item : items) {
                printWriter.println(item.toString());
            }
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(path + " invalid");
        }
    }

    public static List<String> read(String path) {
        List<String> lines = new ArrayList<>();
        try (FileReader fileReader = new FileReader(path);
             BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null && line.trim().length() > 0) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(path + "invalid");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

}
