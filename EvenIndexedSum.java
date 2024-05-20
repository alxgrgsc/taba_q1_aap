import java.util.List;

public class EvenIndexedSum {
    public static double sumEvenIndexedWage(List<String[]> data) {
        return sumEvenIndexedWage(data, 1, 0.0);
    }
    
    private static double sumEvenIndexedWage(List<String[]> data, int index, double sum) {
        //base case
        if (index >= data.size()) {
            return sum;
        }
        //process data in batches to avoid stack overflow error
        int batchSize = 100;
        //iterate over the batch of rows
        for (int i = index; i < Math.min(index + batchSize, data.size()); i++) {
            try {
                // Check if the index is even
                if (i % 2 == 0) {
                    // Parse the Wage value from the current row
                    double wage = Double.parseDouble(data.get(i)[4]); // Assuming wage is in the 5th column (index 4)
                    // Add the wage value to the sum
                    sum += wage;
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.err.println("Error processing row " + i + ": " + e.getMessage());
            }
        }

        //round the sum to 4 decimals
        sum = Math.round(sum * 10000.0) / 10000.0;

        //recursively call for the next batch of rows
        return sumEvenIndexedWage(data, index + batchSize, sum);
    }
}