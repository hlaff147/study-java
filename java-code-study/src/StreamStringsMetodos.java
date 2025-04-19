import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamStringsMetodos {

    public static void main(String[] args) {
        List<String> palavras = Arrays.asList("Maçã", "Banana", "Abacaxi", "Laranja", "Amora");

        List<String> palavrasComA = filtrarPalavrasComA(palavras);
        System.out.println("Palavras com 'A': " + palavrasComA);

        Optional<String> palavrasMaiusculasComA = converterParaMaiusculasComA(palavras);
        palavrasMaiusculasComA.ifPresent(resultado -> System.out.println("Palavras com 'A' em maiúsculas: " + resultado));

        String resultadoJoin = juntarPalavrasMaiusculasComA(palavras);
        System.out.println("Palavras com 'A' em maiúsculas (join): " + resultadoJoin);
    }

    public static List<String> filtrarPalavrasComA(List<String> lista) {
        return lista.stream()
                .filter(palavra -> palavra.toLowerCase().startsWith("a"))
                .toList();
    }

    public static Optional<String> converterParaMaiusculasComA(List<String> lista) {
        return lista.stream()
                .filter(palavra -> palavra.toLowerCase().startsWith("a"))
                .map(String::toUpperCase)
                .reduce((p1, p2) -> p1 + " " + p2);
    }

    public static String juntarPalavrasMaiusculasComA(List<String> lista) {
        return lista.stream()
                .filter(palavra -> palavra.toLowerCase().startsWith("a"))
                .map(String::toUpperCase)
                .collect(Collectors.joining(" "));
    }
}