//imports 
import java.util.*;
import java.io.*;

class SortingThread extends Thread {
    //variables
    List<Employee> data;
    Comparator<Employee> comparator;
    String filename;

    //constructor
    SortingThread(List<Employee> data, Comparator<Employee> comparator, String filename) {
        // Copy the data
        this.data = new ArrayList<>(data);
        this.comparator = comparator;
        this.filename = filename;
    }
    
    //run method
    @Override
    public void run() {
        //sort the data based on the specified comparator
        data.sort(comparator);
        //write the sorted data to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // Write header
            bw.write("Employee Number, First Name, Second Name, Department, Wage, Project Completion Rate");
            bw.newLine();
    
            for (Employee employee : data) {
                bw.write(employee.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}