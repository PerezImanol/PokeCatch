package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import classes.MyException;
import classes.Pokemon;
import classes.Trainer;
import factories.AccountManageableFactory;
import interfaces.AccountManageable;

public class TrainerView extends JDialog implements ActionListener {
	private JTextField textUser;
	private JTextField textPassword;
	private JTextField textOrigin;
	private JTextField textGender;
	private JTextField textBadges;
	private JTextField textPSalv;
	private JTextField textPKRiv;
	private JPanel panelModify;

	public TrainerView() {
		super();
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Trainer View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"
		pestanas.addTab("INFO", panel1);
		panel1.setLayout(null);

		textUser = new JTextField();
		textUser.setBounds(27, 108, 96, 19);
		panel1.add(textUser);
		textUser.setColumns(10);

		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(27, 168, 96, 19);
		panel1.add(textPassword);

		textOrigin = new JTextField();
		textOrigin.setBounds(27, 223, 96, 19);
		panel1.add(textOrigin);
		textOrigin.setColumns(10);

		textGender = new JTextField();
		textGender.setColumns(10);
		textGender.setBounds(219, 168, 96, 19);
		panel1.add(textGender);

		textBadges = new JTextField();
		textBadges.setColumns(10);
		textBadges.setBounds(219, 223, 96, 19);
		panel1.add(textBadges);

		JLabel lblUser = new JLabel("USER");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(27, 73, 76, 25);
		panel1.add(lblUser);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(219, 108, 96, 19);
		panel1.add(dateChooser);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(27, 133, 76, 25);
		panel1.add(lblPassword);

		JLabel lblOriginCity = new JLabel("ORIGIN CITY");
		lblOriginCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblOriginCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOriginCity.setBounds(27, 197, 110, 25);
		panel1.add(lblOriginCity);

		JLabel lblGender = new JLabel("GENDER");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(219, 133, 76, 25);
		panel1.add(lblGender);

		JLabel lblBadges = new JLabel("BADGES");
		lblBadges.setHorizontalAlignment(SwingConstants.LEFT);
		lblBadges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBadges.setBounds(218, 197, 110, 25);
		panel1.add(lblBadges);

		JButton btnSave = new JButton("SAVE");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(129, 312, 85, 21);
		panel1.add(btnSave);

		JTextArea textAreaTrainInfo = new JTextArea();
		textAreaTrainInfo.setBounds(429, 105, 197, 335);
		panel1.add(textAreaTrainInfo);

		JLabel lblAge = new JLabel("AGE");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(219, 73, 76, 25);
		panel1.add(lblAge);

		// Pestaña2
		pestanas.addTab("PC", panel2);
		panel2.setLayout(null);

		JLabel lblPokePC = new JLabel("POKÉMON IN THE PC");
		lblPokePC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokePC.setBounds(29, 64, 153, 13);
		panel2.add(lblPokePC);

		JLabel lblPokemonInfo = new JLabel("POKÉMON INFO");
		lblPokemonInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPokemonInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokemonInfo.setBounds(426, 64, 153, 13);
		panel2.add(lblPokemonInfo);

		JComboBox<String> comboBoxPC = new JComboBox<String>();
		comboBoxPC.setBounds(29, 87, 153, 31);
		panel2.add(comboBoxPC);

		JButton btnPok1 = new JButton("POK1");
		btnPok1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok1.setBounds(29, 171, 85, 21);
		panel2.add(btnPok1);

		JButton btnPok2 = new JButton("POK2");
		btnPok2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok2.setBounds(163, 171, 85, 21);
		panel2.add(btnPok2);

		JButton btnPok3 = new JButton("POK3");
		btnPok3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok3.setBounds(29, 228, 85, 21);
		panel2.add(btnPok3);

		JButton btnPok4 = new JButton("POK4");
		btnPok4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok4.setBounds(163, 228, 85, 21);
		panel2.add(btnPok4);

		JButton btnPok5 = new JButton("POK5");
		btnPok5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok5.setBounds(29, 282, 85, 21);
		panel2.add(btnPok5);

		JButton btnPok6 = new JButton("POK6");
		btnPok6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok6.setBounds(163, 282, 85, 21);
		panel2.add(btnPok6);

		JTextArea textAreaPKInfo = new JTextArea();
		textAreaPKInfo.setEditable(false);
		textAreaPKInfo.setBounds(407, 89, 172, 326);
		panel2.add(textAreaPKInfo);

		// Deshabilitar los botones al inicio
		btnPok1.setEnabled(false);
		btnPok2.setEnabled(false);
		btnPok3.setEnabled(false);
		btnPok4.setEnabled(false);
		btnPok5.setEnabled(false);
		btnPok6.setEnabled(false);

		// Agregar ActionListener a la JComboBox
		comboBoxPC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificar si se ha seleccionado un elemento
				if (comboBoxPC.getSelectedIndex() != -1) {
					// Habilitar los botones
					btnPok1.setEnabled(true);
					btnPok2.setEnabled(true);
					btnPok3.setEnabled(true);
					btnPok4.setEnabled(true);
					btnPok5.setEnabled(true);
					btnPok6.setEnabled(true);
				}
			}
		});

		// Pestaña3
		pestanas.addTab("CAPTURE", panel3);
		panel3.setLayout(null);

		JLabel lblSelecPKCap = new JLabel("Seleccione Pokémon");
		lblSelecPKCap.setBounds(21, 122, 109, 14);
		panel3.add(lblSelecPKCap);

		JLabel lblPKSalv = new JLabel("Pokémon salvaje");
		lblPKSalv.setBounds(432, 94, 86, 14);
		panel3.add(lblPKSalv);

		JComboBox comboBoxSelecPK = new JComboBox();
		comboBoxSelecPK.setBounds(140, 118, 118, 22);
		panel3.add(comboBoxSelecPK);

		textPSalv = new JTextField();
		textPSalv.setEditable(false);
		textPSalv.setBounds(432, 119, 86, 20);
		panel3.add(textPSalv);
		textPSalv.setColumns(10);

		JButton btnLanzarPB = new JButton("LANZAR POKEBALL");
		btnLanzarPB.setBounds(140, 232, 152, 23);
		btnLanzarPB.setEnabled(false); // Deshabilitar el botón inicialmente
		panel3.add(btnLanzarPB);

		JButton btnHuir = new JButton("HUIR");
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHuir.setBounds(400, 232, 86, 23);
		btnHuir.setEnabled(false); // Deshabilitar el botón inicialmente
		panel3.add(btnHuir);

		comboBoxSelecPK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxSelecPK.getSelectedIndex() != -1) { // Si se selecciona una opción
					btnLanzarPB.setEnabled(true); // Habilitar el botón de lanzar pokeball
					btnHuir.setEnabled(true); // Habilitar el botón de huir
				}
			}
		});

		// Pestaña4
		// Pestaña4
		pestanas.addTab("SIMULATION", panel4);
		panel4.setLayout(null);

		JLabel lblSelecPKSim = new JLabel("Selecione Pokémon");
		lblSelecPKSim.setBounds(119, 93, 98, 14);
		panel4.add(lblSelecPKSim);

		JLabel lblPKRiv = new JLabel("Pokémon Rival");
		lblPKRiv.setBounds(378, 93, 84, 14);
		panel4.add(lblPKRiv);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(119, 118, 98, 22);
		panel4.add(comboBox);

		textPKRiv = new JTextField();
		textPKRiv.setBounds(378, 119, 86, 20);
		panel4.add(textPKRiv);
		textPKRiv.setColumns(10);

		JButton btnAtacar = new JButton("ATACAR");
		btnAtacar.setBounds(181, 220, 89, 23);
		btnAtacar.setEnabled(false); // Deshabilitar el botón inicialmente
		panel4.add(btnAtacar);

		JButton btnHuir_1 = new JButton("HUIR");
		btnHuir_1.setBounds(330, 220, 89, 23);
		btnHuir_1.setEnabled(false); // Deshabilitar el botón inicialmente
		panel4.add(btnHuir_1);

		// Agregar ActionListener a la JComboBox
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					btnAtacar.setEnabled(true); // Habilitar el botón "ATACAR"
					btnHuir_1.setEnabled(true); // Habilitar el botón "HUIR"
				}
			}
		});

		// Agregamos el objeto JTabbedPane a la ventana
		getContentPane().add(pestanas);

		// Configuramos la ventana
		setSize(682, 614);
		setVisible(true);
	}

	public static void main(String[] args) {
		new TrainerView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}