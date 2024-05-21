import java.awt.*;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class SortingWindow extends JFrame {
    private String[] columnNames;
    private List<Employee> employees;
    private JButton sortButton;
    private JButton wageSumButton;
    private JButton minValueButton;
    private JTextArea displayArea;
    private JComboBox<String> columnComboBox;
    private int startIndex = 0;

    public SortingWindow(String[] columnNames, List<Employee> employees) {
        super("Sorting Window");
        this.columnNames = columnNames;
        this.employees = employees;
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();

        columnComboBox = new JComboBox<>(columnNames);
        columnComboBox.addActionListener(e -> sortData());
        topPanel.add(columnComboBox);

        sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> sortData());
        topPanel.add(sortButton);

        wageSumButton = new JButton("Calculate Wage Sum(Even Elements)");
        wageSumButton.addActionListener(e -> calculateWageSum());
        topPanel.add(wageSumButton);

        minValueButton = new JButton("Calculate Minimum Wage(Odd Elements)");
        minValueButton.addActionListener(e -> calculateMinValue());
        topPanel.add(minValueButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        displayArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JButton previousButton = new JButton("Previous 20");
        previousButton.addActionListener(e -> showPreviousEntries());
        bottomPanel.add(previousButton, BorderLayout.WEST);

        JButton nextButton = new JButton("Next 20");
        nextButton.addActionListener(e -> showNextEntries());
        bottomPanel.add(nextButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private void sortData() {
        int columnIndex = columnComboBox.getSelectedIndex();
        Comparator<Employee> comparator = Employee.getComparatorByColumn(columnIndex);
        employees.sort(comparator);
        startIndex = 0;
        displayHeaders();
        displayEntries();
    }

    private void displayHeaders() {
        StringBuilder headerBuilder = new StringBuilder();
        for (String columnName : columnNames) {
            headerBuilder.append(columnName).append("\t");
        }
        headerBuilder.append("\n");
        displayArea.setText(headerBuilder.toString());
    }
    
    private void displayEntries() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i < Math.min(startIndex + 20, employees.size()); i++) {
            Employee employee = employees.get(i);
            stringBuilder.append(employee.toString()).append("\n");
        }
        displayArea.append(stringBuilder.toString());
    }

    private void showPreviousEntries() {
        if (startIndex >= 20) {
            startIndex -= 20;
            displayEntries();
        }
    }

    private void showNextEntries() {
        if (startIndex + 20 < employees.size()) {
            startIndex += 20;
            displayEntries();
        }
    }

    private void calculateWageSum() {
        double sum = EvenIndexedSum.sumEvenIndexedWage(employees);
        JOptionPane.showMessageDialog(this, "Sum of every even-indexed row of the wage column: " + String.format("%.1f", sum));
    }

    private void calculateMinValue() {
        double min = FindMinValue.findMinWage(employees);
        JOptionPane.showMessageDialog(this, "Minimum value in the wage column: " + min);
    }
}