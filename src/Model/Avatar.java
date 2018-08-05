package Model;

public class Avatar {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// classe de Avatar para pegar o caminho para cada imagem dependendo da cor e da acao a ser feita
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public String cor; 
	public int acao;
	//0 - Amarelo ; 1 - Azul ; 2 - Laranja ; 3 - Lilas ; 4 - Verde ; 5 - Vermelho
	//0 - Inicio ; 1 - Atirar ; 2 - Defender ; 3 - Recarregar
	//matriz com caminho para as imagens de acordo com a cor e a acao a ser realizada
	private static String[][] matrizCaminho = { {"/std/player_amarelo_std.png", "/atk/player_amarelo_atk.png", "/def/player_amarelo_def.png", "/rel/player_amarelo_rel.png"},
			{"/std/player_azul_std.png", "/atk/player_azul_atk.png",  "/def/player_azul_def.png", "/rel/player_azul_rel.png"},
			{"/std/player_laranja_std.png", "/atk/player_laranja_atk.png","/def/player_laranja_def.png", "/rel/player_laranja_rel.png"},
			{"/std/player_lilas_std.png", "/atk/player_lilas_atk.png", "/def/player_lilas_def.png", "/rel/player_lilas_rel.png"},
			{"/std/player_verde_std.png", "/atk/player_verde_atk.png", "/def/player_verde_def.png", "/rel/player_verde_rel.png"},
			{"/std/player_vermelho_std.png", "/atk/player_vermelho_atk.png", "/def/player_vermelho_def.png", "/rel/player_vermelho_rel.png"},
			{"/std/player_null.png", "/std/player_null.png", "/std/player_null.png", "/std/player_null.png"}};
	
	public Avatar(String cor, int acao) {
		this.cor = cor;
		this.acao = acao;
		
	}
	
	public static String getCaminhoImg (String cor, int acao) {
		
		int corId = 0;
		
		if (cor.equalsIgnoreCase("Amarelo"))
			corId = 0;
		else if (cor.equalsIgnoreCase("Azul"))
			corId = 1;
		else if (cor.equalsIgnoreCase("Laranja"))
			corId = 2;
		else if (cor.equalsIgnoreCase("Lilas"))
			corId = 3;
		else if (cor.equalsIgnoreCase("Verde"))
			corId = 4;
		else if (cor.equalsIgnoreCase("Vermelho"))
			corId = 5;
		else
			corId = 6;
		
		return matrizCaminho[corId][acao];
	}
}
