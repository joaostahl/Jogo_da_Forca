package br.edu.unoesc.jogoDaForca.dados;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Palavras {
    private String palavra_escolhida;
    private static final String caminho = "palavras_jogo_forca.txt";
    private static List<String> palavras;

    static {
        try {
            palavras = Files.readAllLines(Paths.get(caminho));
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de palavras: " + e.getMessage());
            palavras = List.of(); // Lista vazia para evitar NullPointerException
        }
    }
    public static String aleatorizador() {
        if (palavras.isEmpty()) return null;
        Random rand = new Random();
        return palavras.get(rand.nextInt(palavras.size())).toUpperCase();
    }

    public String getPalavra_escolhida() {
        return palavra_escolhida;
    }

    public void setPalavra_escolhida(String palavra_escolhida) {
        this.palavra_escolhida = palavra_escolhida;
    }


}
