package Model;

public class Requisicao implements java.io.Serializable {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// classe de requisicao, pode adicionar mais coisas se necessario, deu ruim quando eu adicionei um objeto inteiro como parametro
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public Long servidorId, acao; //acao: 8 -> verificar status do servidor // 9 -> conectar ao seervidor // 7 -> Usuario pronto para jogar // 1 - Atirar ; 2 - Defender ; 3 - Recarregar
	public Long jogadorId;
	public String jogadorNickname;
	public String jogadorCorAvatar;
	public Long jogadorIdAlvo;
		
}