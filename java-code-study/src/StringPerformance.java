public class StringPerformance {

    // Test concatenation using the '+' operator
    public static long testConcatenationWithPlus(int n) {
        long startTime = System.nanoTime();
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "a";
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Test concatenation using StringBuilder
    public static long testConcatenationWithStringBuilder(int n) {
        long startTime = System.nanoTime();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append("a");
        }
        String finalResult = result.toString();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Run performance tests and compare results
    public static void runPerformanceTests(int n) {
        long timePlus = testConcatenationWithPlus(n);
        long timeStringBuilder = testConcatenationWithStringBuilder(n);

        System.out.println("Concatenation with '+' operator took: " + timePlus + " ns");
        System.out.println("Concatenation with StringBuilder took: " + timeStringBuilder + " ns");

        if (timePlus < timeStringBuilder) {
            System.out.println("Using '+' operator is faster.");
        } else {
            System.out.println("Using StringBuilder is faster.");
        }
    }

    // Main method to execute the tests
    public static void main(String[] args) {
        int n = 100000; // Number of concatenations
        runPerformanceTests(n);
    }
}