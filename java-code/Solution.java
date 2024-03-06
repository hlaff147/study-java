import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
    }
    
    // 2469. Convert the Temperature
    public double[] convertTemperature(double celsius) {
        double[] resuArrays = new double[2];
        resuArrays[0] = celsius + 273.15;
        resuArrays[1] = celsius * 1.8 + 32;
        return resuArrays;
    }
    
}
