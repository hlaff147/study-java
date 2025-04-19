import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StreamNumerosMetodos {

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        List<Integer> pares = filtrarPares(numeros);
        System.out.println("Números pares: " + pares);

        Optional<Integer> somaDobrosPares = calcularSomaDobrosPares(numeros);
        somaDobrosPares.ifPresent(soma -> System.out.println("Soma dos dobros dos pares: " + soma));
    }

    public static List<Integer> filtrarPares(List<Integer> lista) {
        return lista.stream()
                .filter(numero -> numero % 2 == 0)
                .toList();
    }

    public static Optional<Integer> calcularSomaDobrosPares(List<Integer> lista) {
        return lista.stream()
                .filter(numero -> numero % 2 == 0)
                .map(numero -> numero * 2)
                .reduce(Integer::sum);
    }
}