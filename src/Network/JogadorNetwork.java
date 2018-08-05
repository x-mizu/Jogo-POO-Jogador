package Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class JogadorNetwork {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// nao mudei nada aqui, so o nome mesmo
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Socket clientSocket = null;
	private PrintStream os = null;
	private DataOutputStream serverWriter = null;
	private InputStreamReader is = null;
	private BufferedReader serverReader;

	
	public void conectarComServidor() throws IOException {
		clientSocket = new Socket("127.0.0.1", 9000);
		
		int port = clientSocket.getLocalPort();
		System.out.println("------------------\n"+ "Porta: " + port);
		
	    os = new PrintStream(clientSocket.getOutputStream());
	    serverWriter = new DataOutputStream(os);
	    is = new InputStreamReader(clientSocket.getInputStream());
	    serverReader = new BufferedReader(is);
	}
	
	
	public void mandarMensagem(String mensagem) throws IOException{
		serverWriter.writeBytes(mensagem+"\n");
	}
	
	public String receberMensagem() throws IOException {
		return serverReader.readLine();
	}
}
