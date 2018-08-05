package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import Model.Avatar;
import Model.Requisicao;
import Model.Resposta;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interface_Jogo {

	private JFrame frame;
	private BlockingQueue<Requisicao> listaRequisicoes;
	private BlockingQueue<Resposta> listaRespostas;
	private Resposta respostaInicial;
	private String[][] adversarios; // matriz com Id, cor de avatar dos adversarios, nick e acao
	private boolean inicioPartida = true; //variavel para limitar a acao do jogador antes do incio da partida

	//interface jogo deve receber uma lista de requisicoes, de respostas e uma resposta inicial
	public Interface_Jogo(BlockingQueue<Requisicao> listaReq, BlockingQueue<Resposta> listaRes, Resposta respIni) {

		this.listaRequisicoes = listaReq;
		this.listaRespostas = listaRes;
		this.respostaInicial = respIni;
		this.adversarios = buscarAdversarios(respIni.jogadores, respIni.jogadorId);

		initialize();
		this.frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//area do registro de acoes, acao atual e quantidade de balas
		
		JLabel AcaoAtual = new JLabel("");
		
		JLabel lblRegistroDeAcoes = new JLabel("");
		
		JLabel lblQtdDeBalas = new JLabel("Qtd. de Balas:");
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//inicializacao dos avatares dos jogadores e nicknames
		JLabel lblPlayer1 = new JLabel("");

		JLabel lblPlayer2 = new JLabel("");

		JLabel lblPlayer3 = new JLabel("");

		JLabel lblPlayer4 = new JLabel("");

		try {
			ImageIcon img1 = new ImageIcon(ImageIO.read(Interface_Inicio.class.getResourceAsStream(
					Avatar.getCaminhoImg(adversarios[2][0], (int) Long.parseLong(adversarios[3][0])))));
			System.out.println(adversarios[1][0]);
			lblPlayer1.setIcon(img1);

			ImageIcon img2 = new ImageIcon(ImageIO.read(Interface_Inicio.class.getResourceAsStream(
					Avatar.getCaminhoImg(adversarios[2][1], (int) Long.parseLong(adversarios[3][1])))));
			System.out.println(adversarios[1][1]);
			lblPlayer2.setIcon(img2);

			ImageIcon img3 = new ImageIcon(ImageIO.read(Interface_Inicio.class.getResourceAsStream(
					Avatar.getCaminhoImg(adversarios[2][2], (int) Long.parseLong(adversarios[3][2])))));
			System.out.println(adversarios[1][2]);
			lblPlayer3.setIcon(img3);

			ImageIcon img4 = new ImageIcon(ImageIO.read(Interface_Inicio.class.getResourceAsStream(
					Avatar.getCaminhoImg(adversarios[2][3], (int) Long.parseLong(adversarios[3][3])))));
			System.out.println(adversarios[1][3]);
			lblPlayer4.setIcon(img4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JLabel lblP1 = new JLabel(adversarios[1][0]);

		JLabel lblP2 = new JLabel(adversarios[1][1]);

		JLabel lblP3 = new JLabel(adversarios[1][2]);

		JLabel lblP4 = new JLabel(adversarios[1][3]);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Acao dos botoes de atirar
		JButton btnAtirarP1 = new JButton("Atirar");
		btnAtirarP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida && !adversarios[1][0].equals("0")) {
					AcaoAtual.setText(btnAtirarP1.getText() + " em " + adversarios[1][0]);
					
				}
			}
		});

		JButton btnAtirarP2 = new JButton("Atirar");
		btnAtirarP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida && !adversarios[1][1].equals("0")) {
					AcaoAtual.setText(btnAtirarP2.getText() + " em " + adversarios[1][1]);
				}
			}
		});

		JButton btnAtirarP3 = new JButton("Atirar");
		btnAtirarP3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida && !adversarios[1][2].equals("0")) {
					AcaoAtual.setText(btnAtirarP3.getText() + " em " + adversarios[1][2]);
				}
			}
		});

		JButton btnAtirarP4 = new JButton("Atirar");
		btnAtirarP4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida && !adversarios[1][3].equals("0")) {
					AcaoAtual.setText(btnAtirarP4.getText() + " em " + adversarios[1][3]);
				}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//acao dos botoes de recarga, realizar jogada e defender
		JButton btnRecarregar = new JButton("Recarregar");
		btnRecarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida) {

				}
			}
		});

		JButton btnPronto = new JButton("Realizar Jogada");
		btnPronto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!inicioPartida) {

				}
			}
		});

		JButton btnDefender = new JButton("Defender");
		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!inicioPartida) {

				}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// acao do botao de inicio de partida
		JButton btnIniciarPartida = new JButton("Iniciar Partida");
		btnIniciarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				while (inicioPartida) {
					Requisicao req = new Requisicao();
					req.servidorId = respostaInicial.servidorId;
					req.acao = Long.valueOf(7);
					req.jogadorId = respostaInicial.jogadorId;
					listaRequisicoes.add(req);
					
					try { 
						Resposta resp = listaRespostas.take();
						if (resp.serverStatus == 7)
							inicioPartida = false;
						
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblP1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnAtirarP1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPlayer1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(btnRecarregar, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
						.addComponent(btnDefender, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(AcaoAtual, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblQtdDeBalas)
							.addPreferredGap(ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
							.addComponent(btnPronto, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblRegistroDeAcoes, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPlayer2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAtirarP2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblP2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPlayer3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnAtirarP3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblP3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnIniciarPartida, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPlayer4, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAtirarP4, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblP4, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPlayer1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAtirarP1))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPlayer2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAtirarP2)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblP1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblP2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPlayer3, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAtirarP3))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPlayer4, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAtirarP4)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblP4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblP3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
					.addComponent(AcaoAtual, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnDefender)
							.addComponent(btnIniciarPartida))
						.addComponent(lblRegistroDeAcoes, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRecarregar)
						.addComponent(btnPronto)
						.addComponent(lblQtdDeBalas))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);

	}

	public String[][] buscarAdversarios(String[][] jogadores, Long jogadorId) {
		String[][] adversarios = new String[4][4];
		int j = 0;
		for (int i = 0; i < jogadores[0].length; i++) {

			if (Long.parseLong(jogadores[0][i]) != jogadorId) {
				adversarios[0][j] = jogadores[0][i];
				adversarios[1][j] = jogadores[1][i];
				adversarios[2][j] = jogadores[2][i];
				adversarios[3][j] = jogadores[3][i];
				j++;
			}
		}

		return adversarios;
	}
}
