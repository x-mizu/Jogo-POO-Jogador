package Model;

public class Resposta implements java.io.Serializable{
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// classe de resposta, o mesmo comentario da classe de requisicao
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public Long servidorId, serverStatus; //serverStatus: 9 -> Servidor em espera // 8 -> servidor cheio // 7 - servidor em jogo // 1 -> entrou na sala // 0 -> nao entrou na sala //
	public Long jogadorId; //id do jogador caso senha possivel entrar no servidor 
	public int qtdJogadores;
	public String[][] jogadores; 
	//matriz com Id dos jogadores na sala, nick, cor avatar e acao // Id = 0 -> Sem jogador
	//(Id primeira linha, Nick segunda linha, Cor avatar terceira linha, Acao quarta linha) 
	//acao que o jogador fez; // acao atual : 0 -> em espera ; 1 - Atirar ; 2 - Defender ; 3 - Recarregar ; 9 -> morto
}
