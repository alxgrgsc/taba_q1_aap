//imports 
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

//class to create the sorting window
public class SortingWindow extends JFrame {
    //variables
    private List<String[]> data;
    private JComboBox<String> columnComboBox;
    private JButton sortButton;
    private JButton lengthSumButton; // Button for calculating the sum of lengths
    private JButton minValueButton; // New button for calculating the minimum value
    private JTextArea displayArea;
    private int startIndex = 0;

    //constructor
    public SortingWindow(String[] columnNames, List<String[]> data) {
        super("Sorting Window");
        this.data = data;
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        //create the top panel
        JPanel topPanel = new JPanel();
        columnComboBox = new JComboBox<>(columnNames);
        topPanel.add(columnComboBox);

        //create the sort button
        sortButton = new JButton("Sort");
        sortButton.addActionListener(e -> sortData());
        topPanel.add(sortButton);

        //create the length sum button
        lengthSumButton = new JButton("Calculate Wage Sum(Even Elements)"); // Button for length sum
        lengthSumButton.addActionListener(e -> calculateLengthSum());
        topPanel.add(lengthSumButton); // Add the length sum button to the panel

        //create the minimum value button
        minValueButton = new JButton("Calculate Minimum Wage(Odd Elements)"); // New button for minimum value
        minValueButton.addActionListener(e -> calculateMinValue());
        topPanel.add(minValueButton); // Add the new button to the panel

        mainPanel.add(topPanel, BorderLayout.NORTH);

        displayArea = new JTextArea(15, 40);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        //add previous button
        JButton previousButton = new JButton("Previous 20");
        previousButton.addActionListener(e -> showPreviousEntries());
        bottomPanel.add(previousButton, BorderLayout.WEST);

        //add next button
        JButton nextButton = new JButton("Next 20");
        nextButton.addActionListener(e -> showNextEntries());
        bottomPanel.add(nextButton, BorderLayout.EAST);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
    //method to sort the data
    private void sortData() {
        String selectedColumn = (String) columnComboBox.getSelectedItem();
        sortDataByColumn(selectedColumn);
    }

    //method to sort the data by column
    private void sortDataByColumn(String columnName) {
        //get the index of the selected column
        int columnIndex = getColumnIndex(columnName);
        //skip the header row
        List<String[]> dataToSort = data.subList(1, data.size());

        //sort the data based on the column type
        if ("emp_no".equals(columnName)) {
            //sort the data on employee id
            dataToSort.sort(Comparator.comparing(row -> Integer.parseInt(row[columnIndex])));
        } else if ("wage".equals(columnName) || "project_completion_rate".equals(columnName)) {
            //sort the data on wage or project completion rate
            dataToSort.sort(Comparator.comparingDouble(row -> Double.parseDouble(row[columnIndex])));
        } else {
            //
            dataToSort.sort(Comparator.comparing(row -> row[columnIndex]));
        }

        startIndex = 0;
        displayEntries();
    }

    //method to display the entries
    private void displayEntries() {
        StringBuilder stringBuilder = new StringBuilder();
        //display the data in batches of 20
        for (int i = startIndex; i < Math.min(startIndex + 20, data.size()); i++) {
            //get the row data
            String[] rowData = data.get(i);
            //append the row data to the string builder
            for (String cellData : rowData) {
                stringBuilder.append(cellData).append("\t");
            }
            //append a new line
            stringBuilder.append("\n");
        }
        //set the text in the display area
        displayArea.setText(stringBuilder.toString());
    }

    //method to show the previous entries
    private void showPreviousEntries() {
        if (startIndex >= 20) {
            startIndex -= 20;
            displayEntries();
        }
    }

    //method to show the next entries
    private void showNextEntries() {
        if (startIndex + 20 < data.size()) {
            startIndex += 20;
            displayEntries();
        }
    }
    //method to calculate the sum of lengths
    private void calculateLengthSum() {
        double sum = EvenIndexedSum.sumEvenIndexedWage(data);
        JOptionPane.showMessageDialog(this, "Sum of every even-indexed row of the wage column: " + String.format("%.1f", sum));
    }
    //method to calculate the minimum value
    private void calculateMinValue() {
        double min = FindMinValue.findMinValue(data, getColumnIndex("wage")); // Assuming "length" is the column name
        JOptionPane.showMessageDialog(this, "Minimum value in the wage column: " + min);
    }

    //method to get the column index
    private int getColumnIndex(String columnName) {
        String[] columnNames = data.get(0);
        for (int i = 0; i < columnNames.length; i++) {
            if (columnName.equals(columnNames[i])) {
                return i;
            }
        }
        return -1;
    }
}