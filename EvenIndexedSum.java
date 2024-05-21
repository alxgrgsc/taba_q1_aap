//imports 
import java.util.List;

//even indexed sum class
public class EvenIndexedSum {
    public static double sumEvenIndexedWage(List<Employee> employees) {
        return sumEvenIndexedWage(employees, 0, 0.0);
    }
    
    private static double sumEvenIndexedWage(List<Employee> employees, int index, double sum) {
        //base case 
        if (index >= employees.size()) {
            return sum;
        }
        //process data in batches to avoid stack overflow error
        int batchSize = 100;
        //iterate through the employees list
        for (int i = index; i < Math.min(index + batchSize, employees.size()); i++) {
            //check if the index is even(use odd numbers as first entry contains column names)
            if (i % 2 == 1) {
                //get the wage value
                double wage = employees.get(i).getWage();
                //add the wage value to the sum
                sum += wage;
            }
        }



        //call the method recursively
        return sumEvenIndexedWage(employees, index + batchSize, sum);
    }
}