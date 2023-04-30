package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import classes.MyException;
import classes.Trainer;
import factories.AccountManageableFactory;
import interfaces.AccountManageable;

public class ProfessorView extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtNameAndSurname;
	private JTextField txtGender;
	private JTextField txtOriginCity;
	private JTextField txtBadges;
	private JTextField txtPokeballs;
	private JTextField txtAddPokeballs;
	private JTextField txtRegion;
	private JTextField txttrainnernameHasBeen;
	private JTextArea resulttextField;
	private JTextField deleteTrainerTextField;
	private JButton teamButton;
	private JButton combatHistoryButton;
	private JButton informationButton;
	private JComboBox<String> Trainers;
	private AccountManageable manageable = AccountManageableFactory.getAccountManageable();
	private Trainer t;

	public ProfessorView(LoginView loginView, boolean b) {

		super(loginView, b);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorView.class.getResource("/resources/descarga.png")));
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Professor View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		JPanel panelBattle = new JPanel();
		JPanel paneDelete = new JPanel();
		JPanel panelModify = new JPanel();
		JPanel panelAscend = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"

		panelBattle.setBackground(new Color(255, 255, 255));
		pestanas.addTab("BATTLES", panelBattle);

		// Establecemos un layout nulo para el panel
		panelBattle.setLayout(null);

		// Creamos una ComboBox y la agregamos al panel, con tres elementos
		Trainers = new JComboBox<String>();
		Trainers.setMaximumRowCount(20);
		Trainers.setForeground(new Color(0, 0, 0));
		Trainers.setBackground(SystemColor.control);
		Trainers.addItem("");
		
		try {
			for (Trainer element : manageable.getTrainers()) {
				Trainers.addItem(element.getName());
			}
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Trainers.setBounds(175, 61, 237, 53);
		panelBattle.add(Trainers);
		Trainers.addActionListener(this);

		// Creamos un botón llamado "TEAM" y lo agregamos al panel
		teamButton = new JButton("TEAM");
		teamButton.setBounds(23, 204, 142, 49);
		panelBattle.add(teamButton);
		teamButton.addActionListener(this);

		// Creamos un botón llamado "INFORMATION" y lo agregamos al panel
		informationButton = new JButton("INFORMATION");
		informationButton.setBounds(23, 138, 142, 55);
		panelBattle.add(informationButton);
		informationButton.addActionListener(this);

		// Creamos un botón llamado "COMBAT HISTORY" y lo agregamos al panel
		combatHistoryButton = new JButton("COMBAT HISTORY");
		combatHistoryButton.setBounds(23, 264, 142, 49);
		panelBattle.add(combatHistoryButton);
		combatHistoryButton.addActionListener(this);

		// Creamos una etiqueta y la agregamos al panel
		JLabel selectTrainerLabel = new JLabel("Select Trainner:");
		selectTrainerLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		selectTrainerLabel.setBounds(23, 73, 142, 53);
		panelBattle.add(selectTrainerLabel);

		// Creamos un campo de texto y lo agregamos al panel
		resulttextField = new JTextArea(2, 30);
		resulttextField.setBackground(new Color(200, 243, 249));
		resulttextField.setEditable(false);
		resulttextField.setBounds(22, 324, 448, 212);
		panelBattle.add(resulttextField);
	

		// Deshabilitamos los botones por defecto
		informationButton.setEnabled(false);
		teamButton.setEnabled(false);
		combatHistoryButton.setEnabled(false);

		JLabel professorImageLabel = new JLabel("");
		professorImageLabel
				.setIcon(new ImageIcon(ProfessorView.class.getResource("/resources/For_best_wishes_oak.jpg")));
		professorImageLabel.setBounds(444, 100, 188, 341);
		panelBattle.add(professorImageLabel);

		JLabel welcomeProfessorLabel = new JLabel("Welcome Professor !!");
		welcomeProfessorLabel.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 17));
		welcomeProfessorLabel.setBounds(24, 30, 248, 28);
		panelBattle.add(welcomeProfessorLabel);

		
		
		
		// Pestaña2
		
		pestanas.addTab("DELETE", paneDelete);
		paneDelete.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Select Trainner");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 76, 105, 17);
		paneDelete.add(lblNewLabel_1);

		JComboBox<String> trainerDelete = new JComboBox<String>();
		trainerDelete.addItem("");
		trainerDelete.addItem("Entrenador 1");
		trainerDelete.addItem("Entrenador 2");
		trainerDelete.setBounds(162, 71, 222, 31);
		paneDelete.add(trainerDelete);

		JButton checkButton = new JButton("CHECK");
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		checkButton.setBounds(429, 71, 124, 31);
		paneDelete.add(checkButton);

		JButton deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showOptionDialog(panelBattle, "Are you suere you want to delete this trainner?",
						"Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "YES", "NO" }, JOptionPane.YES_OPTION);
			}
		});

		trainerDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkButton.setEnabled(true);
				deleteButton.setEnabled(true);
			}
		});

		// Deshabilitamos los botones por defecto
		checkButton.setEnabled(false);
		deleteButton.setEnabled(false);

		deleteButton.setBounds(354, 349, 124, 31);
		paneDelete.add(deleteButton);

		// Pestaña3
		deleteTrainerTextField = new JTextField();
		deleteTrainerTextField.setBounds(55, 165, 237, 351);
		paneDelete.add(deleteTrainerTextField);
		deleteTrainerTextField.setColumns(10);
		pestanas.addTab("ADD/MODIFY", panelModify);
		panelModify.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("SET INITIAL");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setBounds(151, 263, 86, 14);
		panelModify.add(lblNewLabel_3_1);

		JButton btnNewButton_3 = new JButton("CHECK");
		btnNewButton_3.setBounds(293, 54, 89, 23);
		panelModify.add(btnNewButton_3);

		txtNameAndSurname = new JTextField();
		txtNameAndSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameAndSurname.setText("NAME AND SURNAME");
		txtNameAndSurname.setBounds(40, 55, 193, 20);
		panelModify.add(txtNameAndSurname);
		txtNameAndSurname.setColumns(10);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(40, 137, 101, 20);
		panelModify.add(dateChooser);

		txtGender = new JTextField();
		txtGender.setHorizontalAlignment(SwingConstants.CENTER);
		txtGender.setBounds(40, 166, 101, 20);
		panelModify.add(txtGender);
		txtGender.setColumns(10);

		txtOriginCity = new JTextField();
		txtOriginCity.setHorizontalAlignment(SwingConstants.CENTER);
		txtOriginCity.setColumns(10);
		txtOriginCity.setBounds(40, 197, 101, 20);
		panelModify.add(txtOriginCity);

		txtBadges = new JTextField();
		txtBadges.setHorizontalAlignment(SwingConstants.CENTER);
		txtBadges.setColumns(10);
		txtBadges.setBounds(40, 228, 101, 20);
		panelModify.add(txtBadges);

		JComboBox initialComboBox = new JComboBox();
		initialComboBox.setBounds(40, 259, 101, 22);
		panelModify.add(initialComboBox);

		txtPokeballs = new JTextField();
		txtPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPokeballs.setColumns(10);
		txtPokeballs.setBounds(40, 290, 101, 20);
		panelModify.add(txtPokeballs);

		txtAddPokeballs = new JTextField();
		txtAddPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddPokeballs.setBounds(405, 290, 101, 20);
		panelModify.add(txtAddPokeballs);
		txtAddPokeballs.setColumns(10);

		JButton btnNewButton_4 = new JButton("ADD");
		btnNewButton_4.setBounds(516, 288, 74, 23);
		panelModify.add(btnNewButton_4);

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtener valores de txtPokeballs y txtAddPokeballs
					int pokeballs = Integer.parseInt(txtPokeballs.getText());
					int addPokeballs = Integer.parseInt(txtAddPokeballs.getText());

					// Sumar valores y establecer resultado en txtPokeballs
					txtPokeballs.setText(Integer.toString(pokeballs + addPokeballs));
				} catch (NumberFormatException ex) {
					// Manejar error si los valores no son números enteros
					System.out.println("Error: los valores ingresados no son números enteros");
				}
			}
		});

		JButton btnNewButton_4_1 = new JButton("");
		btnNewButton_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_4_1.setBounds(261, 448, 74, 23);
		panelModify.add(btnNewButton_4_1);

		JLabel lblNewLabel_3 = new JLabel("AGE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(151, 137, 86, 14);
		panelModify.add(lblNewLabel_3);

		JLabel lblNewLabel_3_2 = new JLabel("GENDER");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2.setBounds(151, 168, 86, 14);
		panelModify.add(lblNewLabel_3_2);

		JLabel lblNewLabel_3_2_1 = new JLabel("ORIGIN CITY");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_1.setBounds(151, 199, 86, 14);
		panelModify.add(lblNewLabel_3_2_1);

		JLabel lblNewLabel_3_2_2 = new JLabel("BADGES");
		lblNewLabel_3_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_2.setBounds(151, 230, 86, 14);
		panelModify.add(lblNewLabel_3_2_2);

		JLabel lblNewLabel_3_2_2_1 = new JLabel("POKEBALLS");
		lblNewLabel_3_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_2_1.setBounds(151, 292, 86, 14);
		panelModify.add(lblNewLabel_3_2_2_1);

		// Desactivar los componentes relevantes
		dateChooser.setEnabled(false);
		txtGender.setEnabled(false);
		txtOriginCity.setEnabled(false);
		txtBadges.setEnabled(false);
		initialComboBox.setEnabled(false);
		txtPokeballs.setEnabled(false);
		txtAddPokeballs.setEnabled(false);
		btnNewButton_4.setEnabled(false);
		btnNewButton_4_1.setEnabled(false);

		// Agregar ActionListener al botón btnNewButton_3
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Activar los componentes relevantes
				dateChooser.setEnabled(true);
				txtGender.setEnabled(true);
				txtOriginCity.setEnabled(true);
				txtBadges.setEnabled(true);
				initialComboBox.setEnabled(true);
				txtPokeballs.setEnabled(true);
				txtAddPokeballs.setEnabled(true);
				btnNewButton_4.setEnabled(true);
				btnNewButton_4_1.setEnabled(true);
			}
		});

		// Pestaña4
		pestanas.addTab("ASCEND", panelAscend);
		panelAscend.setLayout(null);

		JLabel lblNewLabel_5_1_1 = new JLabel("LEAF INITIAL");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setBounds(27, 232, 139, 14);
		panelAscend.add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1 = new JLabel("FIRE INITIAL");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(27, 199, 139, 14);
		panelAscend.add(lblNewLabel_5_1);

		JLabel lblNewLabel_5 = new JLabel("WATER INITIAL");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(27, 166, 139, 14);
		panelAscend.add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("Select Trainner");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(243, 58, 147, 14);
		panelAscend.add(lblNewLabel_4);

		JComboBox ascentTrainer = new JComboBox();
		ascentTrainer.addItem("");
		ascentTrainer.addItem("Trainner 1");
		ascentTrainer.addItem("Trainner 2");
		ascentTrainer.setBounds(10, 52, 222, 31);
		panelAscend.add(ascentTrainer);

		txtRegion = new JTextField();
		txtRegion.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegion.setText("REGION");
		txtRegion.setBounds(27, 131, 139, 20);
		panelAscend.add(txtRegion);
		txtRegion.setColumns(10);
		txtRegion.setEnabled(false);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(27, 162, 139, 22);
		panelAscend.add(comboBox_3);
		comboBox_3.setEnabled(false);

		JComboBox comboBox_3_1 = new JComboBox();
		comboBox_3_1.setBounds(27, 195, 139, 22);
		panelAscend.add(comboBox_3_1);
		comboBox_3_1.setEnabled(false);

		JComboBox comboBox_3_2 = new JComboBox();
		comboBox_3_2.setBounds(27, 228, 139, 22);
		panelAscend.add(comboBox_3_2);
		comboBox_3_2.setEnabled(false);

		JButton btnNewButton_5 = new JButton("UPGRADE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelAscend,
						"(trainner_name) has been successfully upgraded to Professor");
			}
		});
		btnNewButton_5.setBounds(203, 172, 122, 69);
		panelAscend.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);

		txttrainnernameHasBeen = new JTextField();
		txttrainnernameHasBeen.setEditable(false);
		txttrainnernameHasBeen.setText("(trainner_name) has been successfully upgraded to Professor");
		txttrainnernameHasBeen.setBounds(335, 196, 316, 20);
		panelAscend.add(txttrainnernameHasBeen);
		txttrainnernameHasBeen.setColumns(10);

		// Deshabilitar elementos al principio
		txtRegion.setEnabled(false);
		comboBox_3.setEnabled(false);
		comboBox_3_1.setEnabled(false);
		comboBox_3_2.setEnabled(false);
		btnNewButton_5.setEnabled(false);

		// Habilitar elementos cuando se seleccione una opción en comboBox_1_1
		ascentTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) ascentTrainer.getSelectedItem();
				if (!selectedOption.equals("")) {
					txtRegion.setEnabled(true);
					comboBox_3.setEnabled(true);
					comboBox_3_1.setEnabled(true);
					comboBox_3_2.setEnabled(true);
					btnNewButton_5.setEnabled(true);
				}
			}
		});

		// Agregamos el objeto JTabbedPane a la ventana
		getContentPane().add(pestanas);

		// Configuramos la ventana
		setSize(682, 614);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Trainers)) {
			resulttextField.setText("");
			informationButton.setEnabled(true);
			teamButton.setEnabled(true);
			combatHistoryButton.setEnabled(true);
			String selectedTrainer = (String)Trainers.getSelectedItem();
			try {
				for (Trainer element : manageable.getTrainers()) {
					if(element.getName().equals(selectedTrainer)) {
						t=element;
						

				
					}
				}
			} catch (MyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
		if(e.getSource().equals(informationButton)) {
			if(t.getName().equals(Trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getTrainerInfo());
				
			}
		}
		if(e.getSource().equals(combatHistoryButton)) {
			if(t.getName().equals(Trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getCombats());
				
			}
		}
			if(e.getSource().equals(teamButton)) {
				if(t.getName().equals(Trainers.getSelectedItem())) {
					resulttextField.setText(null);
					resulttextField.setText(t.getTeamMembers());
					
				}
			}
		}
	}
