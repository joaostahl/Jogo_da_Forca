package br.edu.unoesc.jogoDaForca.visual;

import java.util.Scanner;
import java.util.Set;

public class InterfaceConsole {
    private final Scanner scanner;

    public InterfaceConsole(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarEstado(String palavraVisivel, Set<Character> letrasTentadas, int restantes) {
        System.out.println("Palavra: " + palavraVisivel);
        System.out.println("Letras já tentadas: " + (letrasTentadas.isEmpty() ? "Nenhuma" : letrasTentadas));
        System.out.println("Tentativas restantes: " + restantes);
        System.out.println("-----------------------------");
    }

    public char solicitarLetra() {
        System.out.print("Digite uma letra para adivinhar: ");
        String entrada = scanner.nextLine().trim();

        if (entrada.isEmpty()) {
            return '*';
        }
        return Character.toUpperCase(entrada.charAt(0));
    }
    public String solicitarPalavraCompleta() {
        System.out.print("Qual a palavra correta? Digite seu palpite: ");
        return scanner.nextLine().trim().toUpperCase();
    }
    public void mostrarEstadoFinal(String palavraOriginal, boolean venceu) {
        System.out.println("\n--- FIM DE JOGO ---");
        if (venceu) {
            System.out.println("PARABÉNS! Você VENCEU!");
            System.out.println("A palavra era: " + palavraOriginal);
        } else {
            System.out.println("VOCÊ PERDEU!");
            System.out.println("A palavra correta era: " + palavraOriginal);
        }
        System.out.println("--------------------");
    }

    public boolean solicitarConfirmacao(String pergunta) {
        System.out.print(pergunta);
        String resposta = scanner.nextLine().trim().toLowerCase();
        return resposta.equals("s");
    }
        public int solicitarDificuldade() {
            int escolha = -1;
            int tentativas = 0;

            while (escolha < 1 || escolha > 4) {
                System.out.println("\n--- SELECIONE A DIFICULDADE ---");
                System.out.println("1. Muito Fácil (10 tentativas)");
                System.out.println("2. Fácil (8 tentativas)");
                System.out.println("3. Normal (6 tentativas)");
                System.out.println("4. Difícil (4 tentativas)");
                System.out.print("Digite o número da sua escolha: ");

                try {
                    escolha = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Por favor, digite um número.");
                    escolha = -1;
                }
            }
            switch (escolha) {
                case 1:
                    return 10;
                case 2:
                    return 8;
                case 3:
                    return 6;
                case 4:
                    return 4;
                default:
                    return 6;
            }
        }
}