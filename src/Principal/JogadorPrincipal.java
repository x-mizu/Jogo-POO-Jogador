package Principal;

import java.awt.EventQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.io.*;
import java.net.Socket;

import Interface.Interface_Inicio;
import Interface.Interface_Jogo;
import Model.Requisicao;
import Model.Resposta;
import Network.JogadorNetwork;

//ATENCAO, ESSA CLASSE APENAS E RESPOSAVEL POR PEGAR UM OBJETO DE REQUISICAO DA INTERFACE E ENVIAR PARA A CLASSE NETWORK E VICE VERSA
public class JogadorPrincipal {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JogadorNetwork js = new JogadorNetwork();
        Resposta resp = new Resposta();

        //lista de requisicoes enviada pela interface
        BlockingQueue<Requisicao> listaRequisicoes = new ArrayBlockingQueue<Requisicao>(10);
        BlockingQueue<Resposta> listaRespostas = new ArrayBlockingQueue<Resposta>(10);

        //loop infinito para "Abrir tela de inico" - > "abrir jogo" - > "voltar para a tela de inicio"
        while (true) {
            abrirTelaInicio(listaRequisicoes, listaRespostas);

            try {//TODO fazer um loop para quando a requisicao seja so para verificar o status do server
                js.conectarComServidor();
                Requisicao req1 = listaRequisicoes.take();
                System.out.println(req1.jogadorNickname);
                js.enviarReq(req1);
                //cria uma resposta teste ***para uma resposta de tipo "1", ou seja, de sucesso ao entrar na sala. O servidor deve retornar a matriz de jogadores ja incluso o jogador que entrou na sala
                // ATENCAO, AO APERTAR EM ATUALIZAR STATUS DO SERVIDOR, DA RUIM POIS ESTA RESPOSTA NAO E RESPOSTA PARA ESSE TIPO DE REQUISICAO
                // SEMPRE QUE ENVIAR UMA REQUISICAO, FAZER O PROGRAMA ESPERAR PELA RESPOSTA, ASSIM EVITA MERDAS E VARIAVEIS A MAIS
                //No caso, o Theo entrou na sala, jogador 4
                Resposta teste = js.receberResp();
                System.out.println(teste.serverStatus);
                listaRespostas.add(teste);
                
                abrirTelaJogo(listaRequisicoes, listaRespostas, teste);
                Requisicao req2 = listaRequisicoes.take();
                System.out.println(req2.acao);
                js.enviarReq(req2);
                
                Resposta teste2 = new Resposta();
                teste2 = js.receberResp();
                System.out.println("ID - " + teste2.jogadorId);
                for (int k = 0; k < teste2.jogadores[0].length; k++) {
        			for (int o = 0; o < teste2.jogadores[0].length; o++)
        				System.out.print(teste2.jogadores[k][o]+" ");
        			
        			System.out.println("");
        		}
        		System.out.println("-------------------------------\n");
        		
        		
                listaRespostas.add(teste2);
                

            }//fim do loop
            catch (Exception e) {
                e.printStackTrace();
            }

             //recebe as listas para conversar com o programa principal e a ultima resposta, que foi a de "jogador entrou na sala"

            try {

                //Fiz a classe Requisicao Herdar a classe Jogador jah q contem campos iguais
                while (true) {//vida && numJogadores > 1
                    Requisicao j = listaRequisicoes.take();
                    Requisicao req = new Requisicao();
                    req.jogadorId = j.jogadorId;
                    req.acao = j.acao;
                    if (j.acao == 1) {
                        req.jogadorIdAlvo = j.jogadorIdAlvo;
                    }
                    js.enviarReq(j);
                    resp = js.receberResp();

                    //requisicao de inicio de partida //nao faz muito sentido alterar isso aqui, pq ninguem fez uma acao, nem vai alterar na interface o que vc mudar aqui
//                    System.out.println(j.servidorId + "     " + j.acao + "    " + j.nickname + "   " + j.corAvatar);// teste para ver se a requisicao esta chegando
//                    resp.serverStatus = Long.valueOf(7); // quando todos os usuarios estao pronto para jogar, envia acao de que o servidor esta em jogo
//                    resp.servidorId = Long.valueOf(1);
//                    resp.jogadorId = Long.valueOf(4);
//                    resp.qtdJogadores = 4;
                    listaRespostas.add(resp);
                    //requisicao de jogadas FIZ DUAS REQUISICOES PARA TESTE, VOCE SO DEVE FAZER PARA ELE PEGAR UMA REQUISICAO E ENVIAR UMA RESPOSTA, POIS ESTA EM LOOP
                    //Requisicao j2 = listaRequisicoes.take();
                    System.out.println(j.servidorId + "     " + j.acao + "    " + j.jogadorId + "   " + j.jogadorIdAlvo);// teste para ver se a requisicao esta chegando

//                    String[][] jogadores2 = {{"0", "2", "3", "4", "0"}, {"0", "Michele", "Nelson", "Theo", "0"}, {"0", "Azul", "Verde", "Lilas", "0"}, {"3", "1", "2", "3", "0"}, {"0", "1", "0", "0", "0"}};
//                    resp.jogadores = jogadores2;
//                    resp.serverStatus = Long.valueOf(6); //resultado do turno 
//                    resp.servidorId = Long.valueOf(1);
//                    resp.jogadorId = Long.valueOf(4);
//                    resp.qtdJogadores = 3; //neste turno simleu que um jogador morreu (no caso, eu)
//                    resp.acaoesDaJogada = "Michele atirou em Matheus\n" + "Matheus morreu\n" + "Theo recarregou\n" + "Nelson defendeu\n";
//

                    /*	String[][] jogadores2 = {{"1", "2", "3", "0", "0"}, {"Matheus", "Michele", "Nelson", "0", "0"}, {"Vermelho", "Azul", "Verde", "0", "0"}, {"2", "1", "2", "2", "0"}, {"0", "4", "0", "0", "0"}};
						resp.jogadores = jogadores2;
						resp.serverStatus = Long.valueOf(6); //resultado do turno 
						resp.servidorId = Long.valueOf(1); 
						resp.jogadorId = Long.valueOf(0);
						resp.qtdJogadores = 3; //neste turno simleu que um jogador morreu (no caso, o Theo, ou seja, este jogador)
						resp.acaoesDaJogada = "Michele atirou em Theo\n" + "Matheus defendeu\n"+"Theo morreu\n"+"Nelson defendeu\n";*/
//                    listaRespostas.add(resp);
//                    if (!j.vida || j.qtdJogadores <= 2) {
//                        break;
//                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static void abrirTelaJogo(BlockingQueue<Requisicao> listaReq, BlockingQueue<Resposta> listaRes,
            Resposta respostaInicial) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Interface_Jogo(listaReq, listaRes, respostaInicial);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void abrirTelaInicio(BlockingQueue<Requisicao> listaReq, BlockingQueue<Resposta> listaRes) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Interface_Inicio(listaReq, listaRes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
