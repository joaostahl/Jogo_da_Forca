package br.edu.unoesc.jogoDaForca.modelo;

import java.util.HashSet;
import java.util.Set;

public class PalavraEscolhida {
    private final String palavra;
    private final Set<Character> letrasTentadas = new HashSet<>();

    //Em uppercase para ajudar na comparação depois.
    public PalavraEscolhida(String palavra) {
        this.palavra = palavra.toUpperCase();
    }

    public boolean tentarLetra(char letra) {
        letra = Character.toUpperCase(letra);
        letrasTentadas.add(letra);
        return palavra.indexOf(letra) >= 0;
    }

    public boolean foiCompleta() {
        for (char letra : palavra.toCharArray()) {
            if (!letrasTentadas.contains(letra)) return false;
        }
        return true;
    }

    public String getPalavraVisivel() {
        StringBuilder sb = new StringBuilder();
        for (char c : palavra.toCharArray()) {
            if (letrasTentadas.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public Set<Character> getLetrasTentadas() {
        return letrasTentadas;
    }

    public String getPalavraOriginal() {
        return palavra;
    }
}