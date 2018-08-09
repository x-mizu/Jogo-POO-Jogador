package Model;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class Interface_Inicio {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Create the application.
	 */
	public Interface_Inicio() {
		initialize();	
		this.frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNickname = new JLabel("Nickname:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblAvatar = new JLabel("Avatar:");
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem(makeObj("Amarelo"));
		comboBox.addItem(makeObj("Azul"));
		comboBox.addItem(makeObj("Laranja"));
		comboBox.addItem(makeObj("Lilas"));
		comboBox.addItem(makeObj("Verde"));
		comboBox.addItem(makeObj("Vermelho"));
		
		JLabel lblServidor = new JLabel("Servidor:");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addItem(makeObj("Servidor 1"));
		comboBox_1.addItem(makeObj("Servidor 2"));
		comboBox_1.addItem(makeObj("Servidor 3"));
		comboBox_1.addItem(makeObj("Servidor 4"));
		comboBox_1.addItem(makeObj("Servidor 5"));
		
		JButton btnVerificarStatusDo = new JButton("Verificar Status do Servidor");
		
		JLabel lblStatus = new JLabel("Status:");
		
		JLabel lblOnline = new JLabel("Em Espera");
		lblOnline.setForeground(new Color(0, 0, 255));
		
		JLabel lblQtdDePessoas = new JLabel("Qtd. de pessoas:");
		
		JLabel label = new JLabel("/ 5");
		
		JLabel label_1 = new JLabel("0");
		label_1.setForeground(new Color(0, 128, 0));
		
		JButton btnEntrarNoServidor = new JButton("Entrar no Servidor");
		
		JLabel Avatar_Label = new JLabel();
		
		Image img = new ImageIcon(this.getClass().getResource("/player_amarelo_std.png")).getImage();
		Avatar_Label.setIcon(new ImageIcon(img));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNickname)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblAvatar)
									.addPreferredGap(ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblServidor)
									.addPreferredGap(ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
									.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(30))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(btnEntrarNoServidor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblQtdDePessoas)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblStatus)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblOnline))
							.addComponent(btnVerificarStatusDo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Avatar_Label, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addGap(379))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Avatar_Label, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNickname)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAvatar))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblServidor)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(btnVerificarStatusDo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblStatus)
								.addComponent(lblOnline))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQtdDePessoas)
								.addComponent(label)
								.addComponent(label_1))
							.addGap(51)
							.addComponent(btnEntrarNoServidor)))
					.addContainerGap(117, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
	}

	 private Object makeObj(final String item)  {
	     return new Object() { public String toString() { return item; } };
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
