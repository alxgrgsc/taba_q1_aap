//imports 
import java.util.List;
import java.util.Comparator;

//sort data class
public class SortData {
    //method to sort data
    public static void sortData(List<Employee> employees, String field) {
        //variable
        Comparator<Employee> comparator;
        //switch statement to determine the field to sort by
        switch (field.toLowerCase()) {
            case "empno":
                comparator = Employee.getEmpNoComparator();
                break;
            case "firstname":
                comparator = Employee.getFirstNameComparator();
                break;
            case "secondname":
                comparator = Employee.getSecondNameComparator();
                break;
            case "department":
                comparator = Employee.getDepartmentComparator();
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
        //sort the data based on the specified comparator
        employees.sort(comparator);
    }
}