import java.util.List;

public class EvenIndexedSum {
    public static double sumEvenIndexedWage(List<Employee> employees) {
        return sumEvenIndexedWage(employees, 0, 0.0);
    }
    
    private static double sumEvenIndexedWage(List<Employee> employees, int index, double sum) {
        // Base case
        if (index >= employees.size()) {
            return sum;
        }
        // Process data in batches to avoid stack overflow error
        int batchSize = 100;
        // Iterate over the batch of employees
        for (int i = index; i < Math.min(index + batchSize, employees.size()); i++) {
            // Check if the index is even
            if (i % 2 == 1) {
                // Get the wage value from the current employee
                double wage = employees.get(i).getWage();
                // Add the wage value to the sum
                sum += wage;
            }
        }

        // Round the sum to 4 decimals
        sum = Math.round(sum * 10000.0) / 10000.0;

        // Recursively call for the next batch of employees
        return sumEvenIndexedWage(employees, index + batchSize, sum);
    }
}