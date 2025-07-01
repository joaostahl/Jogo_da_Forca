package br.edu.unoesc.jogoDaForca.app;

import br.edu.unoesc.jogoDaForca.controlador.ControladorDoJogo;

public class Main {
    public static void main(String[] args) {
        ControladorDoJogo jogo = new ControladorDoJogo();
        jogo.iniciar();
    }
}