package Service;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import Model.Interface_Inicio;
import Model.Jogador;

public class JogadorService {

	private Jogador jogador = new Jogador();
	private Interface_Inicio window;
	private Socket clientSocket = null;
	
	private PrintStream os = null;
	private DataOutputStream serverWriter = null;
	private InputStreamReader is = null;
	private BufferedReader serverReader;
	private boolean closed = false;
	
	public void conectarComServidor() throws IOException {
		clientSocket = new Socket("127.0.0.1", 9000);
		boolean connect = true;
		int port = clientSocket.getLocalPort();
		System.out.println("------------------\n"+ "Porta: " + port);
		
	    os = new PrintStream(clientSocket.getOutputStream());
	    serverWriter = new DataOutputStream(os);
	    is = new InputStreamReader(clientSocket.getInputStream());
	    serverReader = new BufferedReader(is);
	}
	
	public void abrirTelaInicio() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new Interface_Inicio();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mandarMensagem(String mensagem) throws IOException{
		serverWriter.writeBytes(mensagem+"\n");
	}
	
	public String receberMensagem() throws IOException {
		return serverReader.readLine();
	}
}
