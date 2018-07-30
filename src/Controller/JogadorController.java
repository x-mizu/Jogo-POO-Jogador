package Controller;

import java.io.IOException;
import java.util.Scanner;

import Service.JogadorService;

public class JogadorController {

	public static void main(String[] args) {
		JogadorService js = new JogadorService();
		Scanner scan = new Scanner(System.in);
		try{
			js.conectarComServidor();
			js.mandarMensagem(scan.nextLine());
			System.out.println(js.receberMensagem());
		}catch (IOException io) {System.out.println(io);}
		
		js.abrirTelaInicio();
	}
}
