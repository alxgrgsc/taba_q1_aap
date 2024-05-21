import java.util.List;
import java.util.Comparator;

public class SortData {

    public static void sortData(List<Employee> employees, String field) {
        Comparator<Employee> comparator;
        switch (field.toLowerCase()) {
            case "firstname":
                comparator = Employee.getFirstNameComparator();
                break;
            case "wage":
                comparator = Employee.getWageComparator();
                break;
            case "projectcompletionrate":
                comparator = Employee.getProjectCompletionRateComparator();
                break;
            default:
                throw new IllegalArgumentException("Invalid field for sorting: " + field);
        }
        employees.sort(comparator);
    }
}
