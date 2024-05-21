//imports 
import java.util.*;

//find min value class
class FindMinValue {
    static double findMinWage(List<Employee> employees) {
        //initialize the min wage to a big number 
        double minWage = 1_000_000;
        //iterate through odd indexes(use even numbers as first entry contains column names) and find the minimum wage
        for (int i = 0; i < employees.size(); i += 2) {
            double wage = employees.get(i).getWage();
            if (wage < minWage) {
                minWage = wage;
            }
        }
        return minWage;
    }
}