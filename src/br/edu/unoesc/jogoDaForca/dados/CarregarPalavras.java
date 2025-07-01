package br.edu.unoesc.jogoDaForca.dados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class CarregarPalavras {
    private static final String CAMINHO = "src/br/edu/unoesc/jogoDaForca/dados/palavras_jogo_forca.txt";
    private static List<String> palavras;

    static {
        try {
            palavras = Files.readAllLines(Paths.get(CAMINHO));
        } catch (IOException e) {
            System.err.println("Erro ao carregar palavras: " + e.getMessage());
            palavras = List.of();
        }
    }

    public static String obterPalavraAleatoria() {
        if (palavras.isEmpty()) return null;
        Random rand = new Random();
        return palavras.get(rand.nextInt(palavras.size())).toUpperCase();
    }
}
