package evenergyapp.Setup;

import java.io.BufferedWriter;
import java.io.File; // Import statement for File
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DataWriter {

    public static void writeToFile(String filename, String data) {
        try {
            File file = new File(filename);
            file.getParentFile().mkdirs();

            try (FileWriter fw = new FileWriter(file, true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}