package Model;

public class Jogador {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// jogador eu peguei do Nelson, so acrescentei algumas variaveis, talvez ate desnecessariamente
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Long id;
	public String nickname;
	public String corAvatar;
	public Jogador alvo;
	public boolean vida;
	public boolean protecao;
	public boolean acaoValida;
	public int acao;
	public int balas;

	public Jogador() {
		this.vida = true;
		this.protecao = false;
		this.balas = 0;
		this.acao = 0;
		this.acaoValida = false;
	}

	public void defender() {
		this.protecao = true;
	}

	public void carregar() {
		this.balas = 1;
	}

	public void atirar(Jogador atirador, Jogador alvo) {
		atirador.balas = 0;
		if (!alvo.protecao) {
			alvo.vida = false;
		}
	}

}
