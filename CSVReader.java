//imports 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private List<Employee> employees = new ArrayList<>(); // List to hold the Employee objects

    public void readCSV(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        // Skip the header
        reader.readLine();
        // Read the file line by line
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            // Create a new Employee object from the CSV row
            Employee employee = new Employee(Integer.parseInt(row[0]), row[1], row[2], row[3], Double.parseDouble(row[4]), Double.parseDouble(row[5]));
            // Add the Employee object to the list
            employees.add(employee);
        }
        reader.close();
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}