import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Create an instance of CSVReader
        CSVReader csvReader = new CSVReader();

        // Read the CSV file
        try {
            csvReader.readCSV("Staff.csv");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        // Get the data from the CSV file
        List<Employee> employees = csvReader.getEmployees();

        // Calculate the sum of the even-indexed rows of the wage column using EvenIndexedSum class
        double sum = EvenIndexedSum.sumEvenIndexedWage(employees);

        // Print the sum
        System.out.printf("Sum of every even-indexed row of the wage column: %.1f\n", sum);

        // Find the minimum value in the wage column using FindMinValue class
        double min = FindMinValue.findMinWage(employees);
        System.out.println("Minimum value in the wage column: " + min);

        // Column names for the SortingWindow
        String[] columnNames = {"Employee Number", "First Name", "Second Name", "Department", "Wage", "Project Completion Rate"};

        // Create an instance of SortingWindow
        SortingWindow sortingWindow = new SortingWindow(columnNames, employees);

        // Display the SortingWindow
        sortingWindow.setVisible(true);

        // Create and start a SortingThread for each column
        for (int i = 0; i < columnNames.length; i++) {
            // Output file path for the sorted data
            String outputFilePath = "sortedStaff_C" + (i + 1) + ".csv";
            SortingThread sortingThread = new SortingThread(new ArrayList<>(employees), Employee.getComparatorByColumn(i), outputFilePath);
            sortingThread.start();
        }
    }
}
