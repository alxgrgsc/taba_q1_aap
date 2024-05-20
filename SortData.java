import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SortData {

    public static void sortData(List<String[]> data, int columnIndex) {
        // Skip the header row for sorting
        List<String[]> dataToSort = data.subList(1, data.size());

        // Determine the type of data in the column and sort accordingly
        if (isIntegerColumn(data, columnIndex)) {
            dataToSort.sort(Comparator.comparing(row -> Integer.parseInt(row[columnIndex])));
        } else if (isDoubleColumn(data, columnIndex)) {
            dataToSort.sort(Comparator.comparingDouble(row -> Double.parseDouble(row[columnIndex])));
        } else {
            dataToSort.sort(Comparator.comparing(row -> row[columnIndex]));
        }

        // Reconstruct the sorted data list including the header row
        List<String[]> sortedData = new ArrayList<>(dataToSort.size() + 1);
        sortedData.add(data.get(0)); // Add the header row
        sortedData.addAll(dataToSort); // Add the sorted rows

        // Update the original data list with the sorted data
        data.clear();
        data.addAll(sortedData);
    }

    private static boolean isIntegerColumn(List<String[]> data, int columnIndex) {
        try {
            Integer.parseInt(data.get(1)[columnIndex]);
            return true;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean isDoubleColumn(List<String[]> data, int columnIndex) {
        try {
            Double.parseDouble(data.get(1)[columnIndex]);
            return true;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
