import java.util.*;

//class to find the minimum value in a column
class FindMinValue {
    static double findMinValue(List<String[]> data, int column) {
        //initialize the minimum value to a large number
        double min = 9_999_999;
        //iterate and find the min value
        for (int i = 1; i < data.size(); i += 2) {
            double value = Double.parseDouble(data.get(i)[column]);
            if (value < min) {
                min = value;
            }
        }
        return min;
    }
}