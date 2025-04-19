import java.util.ArrayList;
import java.util.List;

public class StreamPerformance {

    private static final int SIZE = 100_000_000;
    private static final List<Integer> numbers = new ArrayList<>(SIZE);

    static {
        for (int i = 1; i <= SIZE; i++) {
            numbers.add(i);
        }
    }

    public static void main(String[] args) {
        System.out.println("Executando testes de performance com " + SIZE + " números.");
        testSumOfSquaresOfEvensTraditional();
        testSumOfSquaresOfEvensStream();
        testSumOfSquaresOfEvensParallelStream();
    }

    public static void testSumOfSquaresOfEvensTraditional() {
        long startTime = System.nanoTime();
        long sum = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                sum += (long) number * number;
            }
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // milliseconds
        System.out.println("Soma dos quadrados dos pares (Tradicional): " + sum + " em " + duration + " ms");
    }

    public static void testSumOfSquaresOfEvensStream() {
        long startTime = System.nanoTime();
        long sum = numbers.stream()
                .filter(number -> number % 2 == 0)
                .mapToLong(number -> (long) number * number)
                .sum();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // milliseconds
        System.out.println("Soma dos quadrados dos pares (Stream): " + sum + " em " + duration + " ms");
    }

    public static void testSumOfSquaresOfEvensParallelStream() {
        long startTime = System.nanoTime();
        long sum = numbers.parallelStream()
                .filter(number -> number % 2 == 0)
                .mapToLong(number -> (long) number * number)
                .sum();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // milliseconds
        System.out.println("Soma dos quadrados dos pares (Parallel Stream): " + sum + " em " + duration + " ms");
    }
}