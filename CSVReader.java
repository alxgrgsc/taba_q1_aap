//imports 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//csv reader class
public class CSVReader {
    private List<Employee> employees = new ArrayList<>(); // List to hold the Employee objects

    //method to read the csv file
    public void readCSV(String filePath) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        //skip the first line
        reader.readLine();
        //read the file line by line
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");
            //create an employee object 
            Employee employee = new Employee(Integer.parseInt(row[0]), row[1], row[2], row[3], Double.parseDouble(row[4]), Double.parseDouble(row[5]));
            //add the employee object to the list
            employees.add(employee);
        }
        reader.close();
    }
    //getter for the list of employees
    public List<Employee> getEmployees() {
        return employees;
    }
}