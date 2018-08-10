package Interface;

import java.awt.Color;
import java.awt.Component;
import java.awt.ItemSelectable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import Model.Avatar;
import Model.Jogador;
import Model.Requisicao;
import Model.Resposta;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class Interface_Inicio {

	private JFrame frame;
	private JTextField textField;
	private BlockingQueue<Requisicao> listaRequisicoes;
	private BlockingQueue<Resposta> listaRespostas;
	private Jogador jogador;
	private String avatarCor[] = { "Amarelo", "Azul", "Laranja", "Lilas", "Verde", "Vermelho" };
	private String servidores[] = {"Servidor 1", "Servidor 2", "Servidor 3", "Servidor 4", "Servidor 5"};
	private ImageIcon img = null;
	private Long serverId;
	private String cor;

	//contrutor da interface, recebe uma lista de requisicoes para comunicar com o programa principal
	public Interface_Inicio(BlockingQueue<Requisicao> listaReq, BlockingQueue<Resposta> listaRes) {
		
		this.listaRequisicoes = listaReq;
		this.listaRespostas = listaRes;
		
		this.jogador = new Jogador();
		this.cor = "Amarelo";
		this.serverId = Long.valueOf(1);
		
		initialize();
		this.frame.setVisible(true);
	}

	//metodo para inicializar a janela
	private void initialize() {
		//formato da tela
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//area do Nickname
		JLabel lblNickname = new JLabel("Nickname:");
		textField = new JTextField();
		textField.setColumns(10);
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//selecao de avatar
		JLabel Avatar_Label = new JLabel();
		Avatar_Label.setIcon(new ImageIcon(Interface_Inicio.class.getResource("/std/player_amarelo_std.png")));
		JLabel lblAvatar = new JLabel("Avatar:");
		try {
			img = new ImageIcon(
					ImageIO.read(Interface_Inicio.class.getResourceAsStream(Avatar.getCaminhoImg("Amarelo", 0))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JComboBox comboBox = new JComboBox(avatarCor);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					cor = selectedString(event.getItemSelectable());
					try {
						img = new ImageIcon(
								ImageIO.read(Interface_Inicio.class.getResourceAsStream(Avatar.getCaminhoImg(cor, 0))));
						Avatar_Label.setIcon(img);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		JLabel lblServidor = new JLabel("Servidor:");
		JComboBox comboBox_1 = new JComboBox(servidores);
		comboBox_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String server = selectedString(event.getItemSelectable());
					serverId = Long.parseLong(server.substring((server.length()-1)));
					Requisicao req = new Requisicao();
					req.servidorId = serverId;
					req.acao = Long.valueOf(8);
					listaRequisicoes.add(req);
					
					try { 
						Resposta resp = listaRespostas.take();
					
					} catch (InterruptedException e) {e.printStackTrace();}
				}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//botao para entrar no servidor, ao ser preciosado, pega o Nickname, o servidor, o Avatar e envia uma requisicao com acao 9
		JButton btnEntrarNoServidor = new JButton("Entrar no Servidor");
		btnEntrarNoServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Requisicao req = new Requisicao();
				req.servidorId = serverId;
				req.acao = Long.valueOf(9);
				req.jogadorNickname = textField.getText(); 
				req.jogadorCorAvatar = cor;
				listaRequisicoes.add(req);
				
				try { 
					Resposta resp = listaRespostas.take();
					Long serverStatus = resp.serverStatus;
					if (serverStatus == 1) {
						frame.dispose();
					}
				
				} catch (Exception ie) {ie.printStackTrace();}
			}
		});
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNickname)
									.addGap(4)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAvatar)
									.addGap(126)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblServidor)
									.addGap(114)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEntrarNoServidor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(30)
					.addComponent(Avatar_Label, GroupLayout.PREFERRED_SIZE, 175, Short.MAX_VALUE)
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblNickname))
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblAvatar))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(lblServidor))
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnEntrarNoServidor))
						.addComponent(Avatar_Label, GroupLayout.PREFERRED_SIZE, 175, Short.MAX_VALUE))
					.addGap(175))
		);
		frame.getContentPane().setLayout(groupLayout);

	}

	private String selectedString(ItemSelectable is) {
		Object selected[] = is.getSelectedObjects();
		return ((selected.length == 0) ? "null" : (String) selected[0]);
	}



	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
