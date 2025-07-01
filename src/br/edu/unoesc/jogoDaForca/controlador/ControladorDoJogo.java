package br.edu.unoesc.jogoDaForca.controlador;

import br.edu.unoesc.jogoDaForca.dados.CarregarPalavras;
import br.edu.unoesc.jogoDaForca.modelo.PalavraEscolhida;
import br.edu.unoesc.jogoDaForca.visual.InterfaceConsole;

import java.util.Scanner;

public class ControladorDoJogo {
    private PalavraEscolhida palavra;
    private InterfaceConsole view;
    private int tentativasRestantes;
    private int tentativasIniciaisPorRodada;
    private final Scanner scanner;

    public ControladorDoJogo() {
        this.scanner = new Scanner(System.in);
        this.view = new InterfaceConsole(this.scanner);
    }

    public void iniciar() {
        view.mostrarMensagem("Bem-vindo ao Jogo da Forca!");

        this.tentativasIniciaisPorRodada = view.solicitarDificuldade();
        view.mostrarMensagem("Você selecionou começar cada jogo com " + this.tentativasIniciaisPorRodada + " tentativas!");

        do {

            if (view.solicitarConfirmacao("Deseja alterar a dificuldade para esta nova partida? (s/n): ")) {
                this.tentativasIniciaisPorRodada = view.solicitarDificuldade();
                view.mostrarMensagem("Nova dificuldade selecionada: " + this.tentativasIniciaisPorRodada + " tentativas.");
            }

            reiniciarJogo();
            jogar();

            if (!view.solicitarConfirmacao("Deseja jogar novamente? (s/n): ")) {
                break;
            }
        } while (true);

        view.mostrarMensagem("Obrigado por jogar!");
        scanner.close();
    }

    private void reiniciarJogo() {
        String sorteada = CarregarPalavras.obterPalavraAleatoria();
        if (sorteada == null || sorteada.isEmpty()) {
            view.mostrarMensagem("Não foi possível carregar palavras para o jogo. Verifique o arquivo 'palavras_jogo_forca.txt' e se ele não está vazio.");
            System.exit(1);
        }
        this.palavra = new PalavraEscolhida(sorteada);
        this.tentativasRestantes = this.tentativasIniciaisPorRodada;
        view.mostrarMensagem("\nUm novo jogo começou!");
    }

    private void jogar() {
        while (tentativasRestantes > 0 && !palavra.foiCompleta()) {
            view.mostrarEstado(palavra.getPalavraVisivel(), palavra.getLetrasTentadas(), tentativasRestantes);

            char tentativa = view.solicitarLetra();

            if (!Character.isLetter(tentativa)) {
                view.mostrarMensagem("Entrada inválida. Por favor, digite uma letra.");
                continue;
            }

            if (palavra.getLetrasTentadas().contains(tentativa)) {
                view.mostrarMensagem("Você já tentou a letra '" + tentativa + "'. Tente outra!");
                continue;
            }

            boolean acertou = palavra.tentarLetra(tentativa);
            if (acertou) {
                view.mostrarMensagem("Boa! A letra '" + tentativa + "' está na palavra!");
            } else {
                tentativasRestantes--;
                view.mostrarMensagem("Que pena! A letra '" + tentativa + "' não está na palavra. Tentativas restantes: " + tentativasRestantes);
            }
        }


        if (!palavra.foiCompleta() && tentativasRestantes == 0) {
            view.mostrarMensagem("\nVocê esgotou suas tentativas de letra!");
            boolean querTentarPalavraCompleta = view.solicitarConfirmacao("Gostaria de tentar adivinhar a palavra completa? (s/n): ");

            if (querTentarPalavraCompleta) {
                String palpitePalavra = view.solicitarPalavraCompleta();
                if (palpitePalavra.equalsIgnoreCase(palavra.getPalavraOriginal())) {
                    view.mostrarMensagem("Você acertou a palavra completa! Honra salva!");
                    view.mostrarEstadoFinal(palavra.getPalavraOriginal(), true); // Marca como vitória
                    return;
                } else {
                    view.mostrarMensagem("Que pena! Seu palpite '" + palpitePalavra + "' não está correto.");
                }
            }
        }
        view.mostrarEstadoFinal(palavra.getPalavraOriginal(), palavra.foiCompleta());
    }
}