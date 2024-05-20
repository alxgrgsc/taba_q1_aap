//imports 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//
public class CSVReader {
    private List<String[]> data = new ArrayList<>(); // Array to hold the CSV data
    //method to read a csv file 
    public void readCSV(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        //read the file line by line 
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(","); 
            data.add(row); 
        }
        reader.close();
    }
    //method to get the data from a csv file
    public List<String[]> getData() {
        return data;
    }
}
