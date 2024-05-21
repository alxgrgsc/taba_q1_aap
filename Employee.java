import java.util.Comparator;
import java.util.List;

public class Employee implements Comparable<Employee> {
    private static int idCounter = 0;
    private int emp_no;
    private String first_name;
    private String second_name;
    private String department;
    private double wage;
    private double project_completion_rate;

    // Constructor
    public Employee(Integer emp_no, String first_name, String second_name, String department, double wage, double project_completion_rate) {


        this.emp_no = ++idCounter;
        this.first_name = first_name;
        this.second_name = second_name;
        this.department = department;
        this.wage = wage;
        this.project_completion_rate = project_completion_rate;
    }

    // Method to add employee to list
    public static void addEmployee(List<Employee> employees, Employee newEmployee) {
        employees.add(newEmployee);
    }

    // Getters
    public int getEmpNo() {
        return emp_no;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getSecondName() {
        return second_name;
    }

    public String getDepartment() {
        return department;
    }

    public double getWage() {
        return wage;
    }

    public double getProjectCompletionRate() {
        return project_completion_rate;
    }

    // Implement Comparable interface
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.emp_no, other.emp_no);
    }

    // Static comparator methods for sorting
    public static Comparator<Employee> getFirstNameComparator() {
        return Comparator.comparing(Employee::getFirstName);
    }

    public static Comparator<Employee> getWageComparator() {
        return Comparator.comparing(Employee::getWage);
    }

    public static Comparator<Employee> getProjectCompletionRateComparator() {
        return Comparator.comparing(Employee::getProjectCompletionRate);
    }

    public static Comparator<Employee> getComparatorByColumn(int column) {
        switch (column) {
            case 0:
                return Comparator.comparing(Employee::getEmpNo);
            case 1:
                return getFirstNameComparator();
            case 2:
                return Comparator.comparing(Employee::getSecondName);
            case 3:
                return Comparator.comparing(Employee::getDepartment);
            case 4:
                return getWageComparator();
            case 5:
                return getProjectCompletionRateComparator();
            default:
                throw new IllegalArgumentException("Invalid column index: " + column);
        }
    }

    @Override
    public String toString() {
        return emp_no +
            ",  '" + first_name + '\'' +
            ",  '" + second_name + '\'' +
            ",  '" + department + '\'' +
            ",  " + wage +
            ",  " + project_completion_rate;
    }
}

// Exceptions
class InvalidFirstNameException extends Exception {
    public InvalidFirstNameException() {
        super("Employee first name cannot be empty and cannot consist of only digits. Please correct this.");
    }
}

class InvalidSecondNameException extends Exception {
    public InvalidSecondNameException() {
        super("Employee second name cannot be empty and cannot consist of only digits. Please correct this.");
    }
}

class InvalidEmployeeNumberException extends Exception {
    public InvalidEmployeeNumberException() {
        super("Employee number must be a non-negative integer. Please correct this.");
    }
}

class InvalidWageException extends Exception {
    public InvalidWageException() {
        super("Employee wage must be a positive number. Please correct this.");
    }
}

class InvalidDepartmentException extends Exception {
    public InvalidDepartmentException() {
        super("Employee department cannot be empty. Employee department cannot have only numbers. Please correct this.");
    }
}

class InvalidProjectCompletionRateException extends Exception {
    public InvalidProjectCompletionRateException() {
        super("Employee project completion rate must be between 0 and 100. Please correct this.");
    }
}


