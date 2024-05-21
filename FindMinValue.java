import java.util.*;

class FindMinValue {
    static double findMinWage(List<Employee> employees) {
        // Initialize the minimum wage to a large number
        double minWage = Double.MAX_VALUE;
        // Iterate and find the min wage in odd elements
        for (int i = 0; i < employees.size(); i += 2) {
            double wage = employees.get(i).getWage();
            if (wage < minWage) {
                minWage = wage;
            }
        }
        return minWage;
    }
}