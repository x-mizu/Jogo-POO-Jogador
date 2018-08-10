package Network;

import Model.Requisicao;
import Model.Resposta;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogadorNetwork {
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// nao mudei nada aqui, so o nome mesmo
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	private Socket clientSocket = null;
	private PrintStream os = null;
	private DataOutputStream serverWriter = null;
	private InputStreamReader is = null;
	private BufferedReader serverReader;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;

	public void conectarComServidor(int serverPort) throws IOException {
		clientSocket = new Socket("127.0.0.1", serverPort);

		int port = clientSocket.getLocalPort();
		System.out.println("------------------\n" + "Porta: " + port);

		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
	}

	// RECEBE UM OBJETO DO TIPO REQUISCAO E ENVIA //
	public void enviarReq(Requisicao req) {
		try {
			out.writeObject(req);
		} catch (IOException ex) {
			Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// RECEBE UM OBJETO DO TIPO RESPOSTA //
	public Resposta receberResp() {
		try {
			Resposta resp2 = (Resposta)in.readObject();
			System.out.println("ID - " + resp2.jogadorId);
			for (int k = 0; k < resp2.jogadores.length ; k++) {
            	for (int l = 0 ; l < resp2.jogadores[0].length; l++)
            		System.out.print(resp2.jogadores[k][l]+ " ");
            	
            	System.out.println("");
            }
            System.out.println("----------------------------------\n");
			
			return resp2;
		} catch (IOException ex) {
			Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}
