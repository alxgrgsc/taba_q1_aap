//imports
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class SortData {

    public static void sortData(List<String[]> data, int columnIndex) {
        //skip the header row
        List<String[]> dataToSort = data.subList(1, data.size());

        //compare the data depending on the column type
        if (isIntegerColumn(data, columnIndex)) {
            dataToSort.sort(Comparator.comparing(row -> Integer.parseInt(row[columnIndex])));
        } else if (isDoubleColumn(data, columnIndex)) {
            dataToSort.sort(Comparator.comparingDouble(row -> Double.parseDouble(row[columnIndex])));
        } else {
            dataToSort.sort(Comparator.comparing(row -> row[columnIndex]));
        }

        //create a new list to store the sorted data
        List<String[]> sortedData = new ArrayList<>(dataToSort.size() + 1);
        sortedData.add(data.get(0));
        sortedData.addAll(dataToSort); 

        //clear the original data and add the sorted data
        data.clear();
        data.addAll(sortedData);
    }

    //check if the column is an integer column
    private static boolean isIntegerColumn(List<String[]> data, int columnIndex) {
        try {
            Integer.parseInt(data.get(1)[columnIndex]);
            return true;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }

    //check if the column is a double column
    private static boolean isDoubleColumn(List<String[]> data, int columnIndex) {
        try {
            Double.parseDouble(data.get(1)[columnIndex]);
            return true;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return false;
        }
    }
}
