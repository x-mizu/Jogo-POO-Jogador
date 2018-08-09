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
        
        
	public void conectarComServidor() throws IOException {
		clientSocket = new Socket("127.0.0.1", 9000);
		
		int port = clientSocket.getLocalPort();
		System.out.println("------------------\n"+ "Porta: " + port);
		
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
	}
	
        //              RECEBE UM OBJETO DO TIPO REQUISCAO E ENVIA          //
        public void enviarReq(Requisicao req)
        {
            try {
                out.writeObject(req);
            } catch (IOException ex) {
                Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        //          RECEBE UM OBJETO DO TIPO RESPOSTA               //
        public Resposta receberResp() 
        {
           Resposta resposta = new Resposta(); 
            try {
                resposta = (Resposta)in.readObject();
            } catch (IOException ex) {
                Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(JogadorNetwork.class.getName()).log(Level.SEVERE, null, ex);
            }
            return resposta;
        }
}
