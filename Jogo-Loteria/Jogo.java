import java.util.*;

public class Jogo {

    // Constantes
    public static final int NUMERO_MINIMO = 1;
    public static final int NUMERO_MAXIMO = 60;
    private static final int QUANTIDADE_SORTEIO = 6;

    // Método para calcular prêmio
    public double calcularPremio(List<Integer> numerosApostados, double valorApostado) {
        if (!validaNumeros(numerosApostados)) {
            return 0.0; // Aposta inválida
        }

        Set<Integer> numerosSorteados = sortearNumeros();
        int acertos = contarAcertos(numerosApostados, numerosSorteados);

        return calcularValorPremio(acertos, valorApostado);
    }

    // Valida números apostados
    private boolean validaNumeros(List<Integer> numeros) {
        Set<Integer> numerosValidos = new HashSet<>();
        for (Integer numero : numeros) {
            if (numero < NUMERO_MINIMO || numero > NUMERO_MAXIMO || !numerosValidos.add(numero)) {
                return false;
            }
        }
        return numeros.size() >= 6 && numeros.size() <= 15;
    }

    // Sorteia números
    private Set<Integer> sortearNumeros() {
        Set<Integer> numerosSorteados = new HashSet<>(); //Impede que tenham numeros duplicados
        Random random = new Random();
        while (numerosSorteados.size() < QUANTIDADE_SORTEIO) {
            numerosSorteados.add(random.nextInt(NUMERO_MAXIMO) + 1);
        }
        return numerosSorteados;
    }

    // Conta acertos
    private int contarAcertos(List<Integer> numerosApostados, Set<Integer> numerosSorteados) {
        int acertos = 0;
        for (Integer apostado : numerosApostados) {
            if (numerosSorteados.contains(apostado)) {
                acertos++; //Ao inves de usar acertos + 1, usei o ++ que faz a mesma coisa
            }
        }
        return acertos;
    }

    // Calcula valor do prêmio
    private double calcularValorPremio(int acertos, double valorApostado) {
        switch (acertos) {
            case 6:
                return valorApostado; // 100%
            case 5:
                return valorApostado * 0.2; // 20%
            case 4:
                return valorApostado * 0.05; // 5%
            default:
                return 0.0;
        }
    }
}
