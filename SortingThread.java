//imports 
import java.util.*;
import java.io.*;

class SortingThread extends Thread {
    //variables
    List<String[]> data;
    int column;
    String filename;

    //constructor
    SortingThread(List<String[]> data, int column, String filename) {
        // Skip the first row (header row) when copying the data
        this.data = new ArrayList<>(data.subList(0, data.size()));
        this.column = column;
        this.filename = filename;
    }
    
    //run method
    @Override
    public void run() {
        //sort the data based on the specified column
        SortData.sortData(data, column);
        //write the sorted data to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                bw.write(String.join(",", row));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}