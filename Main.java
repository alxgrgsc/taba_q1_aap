import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //create an instance of CSVReader
        CSVReader csvReader = new CSVReader();

        //read the CSV file
        try {
            csvReader.readCSV("Staff.csv");
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        //get the data from the CSV file
        List<String[]> csvData = csvReader.getData();

        //calculate the sum of the even-indexed rows of the wage column
        double sum = EvenIndexedSum.sumEvenIndexedWage(csvData);

        //print the sum
        System.out.printf("Sum of every even-indexed row of the wage column: %.1f\n", sum);

        //find the minimum value in the length column
        double min = FindMinValue.findMinValue(csvData, 4);
        System.out.println("Minimum value in the wage column: " + min);

        //column names for the SortingWindow
        String[] columnNames = {"emp_no", "first_name", "second_name", "department", "wage", "project_completion_rate"};

        //create an instance of SortingWindow
        SortingWindow sortingWindow = new SortingWindow(columnNames, csvData);

        //display the SortingWindow
        sortingWindow.setVisible(true);

        //create and start a SortingThread for each column
        for (int i = 0; i < columnNames.length; i++) {
            //output file path for the sorted data
            String outputFilePath = "sortedStaff_C" + (i + 1) + ".csv";
            SortingThread sortingThread = new SortingThread(csvData, i, outputFilePath);
            sortingThread.start();
        }
}
}
