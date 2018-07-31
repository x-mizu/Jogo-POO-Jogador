package Model;

import jogo.Jogador;

public class Jogador {

	{
	    String ID;
	    Jogador alvo;
	    boolean vida;
	    boolean protecao;
	    boolean acaoValida;
	    int acao;
	    boolean balas;

	public Jogador(String nome) {
		this.ID = nome;
		this.vida = true;
		this.protecao = false;
		this.balas = false;
		this.acao = 0;
		this.acaoValida = false;
	}

	public void defender() {
		this.protecao = true;
	}

	public void carregar() {
		this.balas = true;
	}

	public void atirar(Jogador atirador, Jogador alvo) {
		atirador.balas = false;
		if (!alvo.protecao) {
			alvo.vida = false;
		}
	}
}
