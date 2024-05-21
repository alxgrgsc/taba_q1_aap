//imports 
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//main class
public class Main {

    public static void main(String[] args) {
        //create an instance of csvreader 
        CSVReader csvReader = new CSVReader();

        //read the csv file
        try {
            csvReader.readCSV("Staff.csv");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        //get the list of employees
        List<Employee> employees = csvReader.getEmployees();

        //calculate the sum of every even-indexed row of the wage column 
        double sum = EvenIndexedSum.sumEvenIndexedWage(employees);

        //print the sum 
        System.out.printf("Sum of every even-indexed row of the wage column: %.1f\n", sum);

        //find the minimum wage value in odd-indexed row of the wage column
        double min = FindMinValue.findMinWage(employees);
        System.out.println("Minimum value in the wage column: " + min);

        //column names
        String[] columnNames = {"Employee Number", "First Name", "Second Name", "Department", "Wage", "Project Completion Rate"};

        //create an instance of sorting window
        SortingWindow sortingWindow = new SortingWindow(columnNames, employees);

        //display the sorting window
        sortingWindow.setVisible(true);

        //create a thread for each column to sort the data
        for (int i = 0; i < columnNames.length; i++) {
            //write the sorted data to a file
            String outputFilePath = "sortedStaff_C" + (i + 1) + ".csv";
            SortingThread sortingThread = new SortingThread(new ArrayList<>(employees), Employee.getComparatorByColumn(i), outputFilePath);
            sortingThread.start();
        }
    }
}
