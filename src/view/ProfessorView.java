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

import classes.Combat;
import classes.MyException;
import classes.Pokemon;
import classes.Trainer;
import factories.AccountManageableFactory;
import interfaces.AccountManageable;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

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
	private JTextArea deleteTrainerTextArea;
	private JButton teamButton;
	private JButton combatHistoryButton;
	private JButton informationButton;
	private JButton checkButton;
	private JButton deleteButton;
	private JComboBox<String> trainers;
	private JComboBox<Pokemon> initialComboBox;
	private JComboBox<String> ascentTrainer;
	private JComboBox<String> waterComboBox;
	private JComboBox<String> fireComboBox;
	private JComboBox<String> grassComboBox;
	private JComboBox<String> trainerDelete;
	private AccountManageable manageable = AccountManageableFactory.getAccountManageable();
	private Trainer t;
	private JPanel panelBattle;
	private JPanel paneDelete;
	private JPanel panelModify;
	private JPanel panelAscend;
	private JTable table;
	private JTable table2;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane;

	
	public ProfessorView(LoginView loginView, boolean b) {

		super(loginView, b);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorView.class.getResource("/resources/descarga.png")));
		setTitle("Professor View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		//Here the different panels of the whole view are defined
		panelBattle = new JPanel();
		paneDelete = new JPanel();
		panelModify = new JPanel();
		panelAscend = new JPanel();

		
		JTabbedPane pestanas = new JTabbedPane();

		//1.Battles View
		panelBattle.setBackground(new Color(255, 255, 255));
		pestanas.addTab("BATTLES", panelBattle);

		
		panelBattle.setLayout(null);
		
		//Here we specify the trainers the professor can choose from to make different actions 
		trainers = new JComboBox<String>();
		trainers.setMaximumRowCount(20);
		trainers.setForeground(new Color(0, 0, 0));
		trainers.setBackground(SystemColor.control);
		trainers.addItem("");

		//This is the method used to add every Trainer's name
		try {
			for (Trainer element : manageable.getTrainers()) {
				trainers.addItem(element.getName());
			}
		} catch (MyException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
		trainers.setBounds(175, 61, 237, 53);
		panelBattle.add(trainers);
		trainers.addActionListener(this);

		
		teamButton = new JButton("TEAM");
		teamButton.setBounds(10, 204, 142, 49);
		panelBattle.add(teamButton);
		teamButton.addActionListener(this);

		
		informationButton = new JButton("INFORMATION");
		informationButton.setBounds(10, 138, 142, 55);
		panelBattle.add(informationButton);
		informationButton.addActionListener(this);

		combatHistoryButton = new JButton("COMBAT HISTORY");
		combatHistoryButton.setBounds(10, 264, 142, 49);
		panelBattle.add(combatHistoryButton);
		combatHistoryButton.addActionListener(this);

		JLabel selectTrainerLabel = new JLabel("Select Trainner:");
		selectTrainerLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		selectTrainerLabel.setBounds(23, 73, 142, 53);
		panelBattle.add(selectTrainerLabel);

		
		/*This one is the TextArean where the trainers info is going to be shown it is an area and not a normal text field because 
		 * it has to show more than one line and a label can not handle it */
		resulttextField = new JTextArea(2, 30);
		resulttextField.setBackground(new Color(200, 243, 249));
		resulttextField.setEditable(false);
		resulttextField.setBounds(162, 171, 280, 82);
		panelBattle.add(resulttextField);

		informationButton.setEnabled(false);
		teamButton.setEnabled(false);
		combatHistoryButton.setEnabled(false);

		//The JLabel under this comment has no text because the picture of Oak is on this Label
		JLabel professorImageLabel = new JLabel("");
		professorImageLabel
				.setIcon(new ImageIcon(ProfessorView.class.getResource("/resources/For_best_wishes_oak.jpg")));
		professorImageLabel.setBounds(463, 97, 188, 341);
		panelBattle.add(professorImageLabel);

		JLabel welcomeProfessorLabel = new JLabel("Welcome Professor !!");
		welcomeProfessorLabel.setFont(new Font("Yu Gothic", Font.BOLD | Font.ITALIC, 17));
		welcomeProfessorLabel.setBounds(24, 30, 248, 28);
		panelBattle.add(welcomeProfessorLabel);

		
		/*Here we define two different tables to show the information of the Pokemon team and the combat History
		 * The one Responsible for the Pokemon team is the scrollPane, tableModel and table.
		 * the same unes but with the number 2 are the ones fore Combat history*/ 
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 350, 432, 118);
		panelBattle.add(scrollPane);

		tableModel = new DefaultTableModel();
		String[] tableHeaders = { "PokedexID", "Region", "Name", "Nickname", "type1", "type2", "level" };
		tableModel.setColumnIdentifiers(tableHeaders);
		table = new JTable(tableModel);
		table.setEnabled(false);

		table.setBackground(new Color(239, 205, 124));
		scrollPane.setViewportView(table);
		table.setVisible(false);
		scrollPane.setVisible(false);

		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 350, 432, 118);
		panelBattle.add(scrollPane2);

		tableModel2 = new DefaultTableModel();
		String[] tableHeaders2 = { "TrainerID1","TrainerID2","WinnerID" };
		tableModel2.setColumnIdentifiers(tableHeaders2);
		table2 = new JTable(tableModel2);
		table2.setEnabled(false);

		/*As you can tell both tables have the same position and thats because they are shown in the same places 
		 * and the ActionListener is the one responsible of managing if one of the is visible or not not*/
		table2.setBackground(new Color(239, 205, 124));
		scrollPane2.setViewportView(table2);
		table2.setVisible(false);
		scrollPane2.setVisible(false);

		
		//2.Delete View

		pestanas.addTab("DELETE", paneDelete);
		paneDelete.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Which trainer do you want to delete?");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(27, 26, 337, 17);
		paneDelete.add(lblNewLabel_1);

		
		//Here we do the same thing as we do above for the trainersComboBox
		trainerDelete = new JComboBox<String>();
		trainerDelete.addItem("");
		try {
			for (Trainer element : manageable.getTrainers()) {
				trainerDelete.addItem(element.getName());
			}
		} catch (MyException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}
		trainerDelete.setBounds(37, 63, 222, 47);
		paneDelete.add(trainerDelete);
		trainerDelete.addActionListener(this);

		//And here are the two different buttons used in the second view
		checkButton = new JButton("CHECK");
		checkButton.setBounds(37, 136, 173, 47);
		paneDelete.add(checkButton);
		checkButton.addActionListener(this);

		deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(this);

		checkButton.setEnabled(false);
		deleteButton.setEnabled(false);

		deleteButton.setBounds(37, 201, 173, 47);
		paneDelete.add(deleteButton);

		//As the previous page here we have the TextArea where the trainer info is shown
		deleteTrainerTextArea = new JTextArea();
		deleteTrainerTextArea.setBackground(SystemColor.inactiveCaption);
		deleteTrainerTextArea.setBounds(79, 270, 285, 82);
		paneDelete.add(deleteTrainerTextArea);
		deleteTrainerTextArea.setColumns(10);
		
		// Pestaña3
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProfessorView.class.getResource("/resources/Ash_Ketchum_Journeys.png")));
		lblNewLabel.setBounds(415, 26, 216, 472);
		paneDelete.add(lblNewLabel);
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

		initialComboBox = new JComboBox<Pokemon>();
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

		JLabel lblNewLabel_5_1_1 = new JLabel("GRASS INITIAL");
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

		ascentTrainer = new JComboBox<String>();
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

		waterComboBox = new JComboBox<String>();
		waterComboBox.setBounds(27, 162, 139, 22);
		panelAscend.add(waterComboBox);
		waterComboBox.setEnabled(false);

		fireComboBox = new JComboBox<String>();
		fireComboBox.setBounds(27, 195, 139, 22);
		panelAscend.add(fireComboBox);
		fireComboBox.setEnabled(false);

		grassComboBox = new JComboBox<String>();
		grassComboBox.setBounds(27, 228, 139, 22);
		panelAscend.add(grassComboBox);
		grassComboBox.setEnabled(false);

		JButton upgradeButton = new JButton("UPGRADE");
		upgradeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panelAscend,
						"(trainner_name) has been successfully upgraded to Professor");
			}
		});
		upgradeButton.setBounds(203, 172, 122, 69);
		panelAscend.add(upgradeButton);
		upgradeButton.setEnabled(false);

		txttrainnernameHasBeen = new JTextField();
		txttrainnernameHasBeen.setEditable(false);
		txttrainnernameHasBeen.setText("(trainner_name) has been successfully upgraded to Professor");
		txttrainnernameHasBeen.setBounds(335, 196, 316, 20);
		panelAscend.add(txttrainnernameHasBeen);
		txttrainnernameHasBeen.setColumns(10);

		// Deshabilitar elementos al principio
		txtRegion.setEnabled(false);
		waterComboBox.setEnabled(false);
		fireComboBox.setEnabled(false);
		grassComboBox.setEnabled(false);
		upgradeButton.setEnabled(false);

		// Habilitar elementos cuando se seleccione una opción en comboBox_1_1
		ascentTrainer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) ascentTrainer.getSelectedItem();
				if (!selectedOption.equals("")) {
					txtRegion.setEnabled(true);
					waterComboBox.setEnabled(true);
					fireComboBox.setEnabled(true);
					grassComboBox.setEnabled(true);
					upgradeButton.setEnabled(true);
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

		/*This first if control if something has been chosen on the trainers conboBox and 
		 * by using the name identifies the trainer and sets it to the t variable   */
		if (e.getSource().equals(trainers)) {
			resulttextField.setText("");
			informationButton.setEnabled(true);
			teamButton.setEnabled(true);
			combatHistoryButton.setEnabled(true);
			String selectedTrainer = (String) trainers.getSelectedItem();
			try {
				for (Trainer element : manageable.getTrainers()) {
					if (element.getName().equals(selectedTrainer)) {
						t = element;

					}
				}
			} catch (MyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}

		}
		if (e.getSource().equals(informationButton)) {
			if (t.getName().equals(trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getTrainerInfo());

			}
		}
		//Here are the two methods to show and hide the tables stated before
		if (e.getSource().equals(teamButton)) {
			if (t.getName().equals(trainers.getSelectedItem())) {
				scrollPane.setVisible(true);
				scrollPane2.setVisible(false);
				table.setVisible(true);
				table2.setVisible(false);
				tableModel.setRowCount(0);
				try {
					for (Trainer element : manageable.getTrainers()) {
						if (element.getName().equals(trainers)) {
							t = element;

						}
					}
				} catch (MyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

				for (Pokemon element : t.getTeam()) {
					Object[] row = new Object[7];
					row[0] = element.getPokedexID();
					row[1] = element.getName();
					row[2] = element.getNickname();
					row[3] = element.getRegion();
					row[4] = element.getType1();
					row[5] = element.getType2();
					row[6] = element.getLevel();
					tableModel.addRow(row);
				}
table.setVisible(true);
			}
		}
		if (e.getSource().equals(combatHistoryButton)) {
			if (t.getName().equals(trainers.getSelectedItem())) {
				scrollPane.setVisible(false);
				scrollPane2.setVisible(true);
				table.setVisible(false);
				table2.setVisible(true);
				tableModel2.setRowCount(0);
				try {
					for (Trainer element : manageable.getTrainers()) {
						if (element.getName().equals(trainers)) {
							t = element;

						}
					}
				} catch (MyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

				}

				for (Combat element : t.getCombatHistory()) {
					Object[] row = new Object[3];
					row[0] = element.getTrainer1();
					row[1] = element.getTrainer2();
					row[2] = element.getWinnerTrainerID();

					tableModel2.addRow(row);
				}

			}
		}
	
		if (e.getSource().equals(trainerDelete)) {
			
			deleteButton.setEnabled(true);
			checkButton.setEnabled(true);
			String selectedTrainer = (String) trainerDelete.getSelectedItem();
			try {
				for (Trainer element : manageable.getTrainers()) {
					if (element.getName().equals(selectedTrainer)) {
						t = element;

					}
				}
			} catch (MyException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource().equals(checkButton)) {
			if (t.getName().equals(trainerDelete.getSelectedItem())) {
				deleteTrainerTextArea.setText(null);
				deleteTrainerTextArea.setText(t.getTrainerInfo());

			}
		}
		if (e.getSource().equals(deleteButton)) {
			if (JOptionPane.showOptionDialog(panelBattle, "Do you want to delete this trainner?", "Confirm delete",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] { "YES", "NO" },
					JOptionPane.YES_OPTION) == 0) {
				int id;
				id = t.getTrainerID();
				try {
					manageable.deleteTrainer(id);
					JOptionPane.showMessageDialog(null, "The trainer has been sucssesfully deleted");
					String elementToRemove = t.getName();
					for (int i = 0; i < trainerDelete.getItemCount(); i++) {
						String element = trainerDelete.getItemAt(i);
						if (element.equals(elementToRemove)) {
							trainerDelete.removeItemAt(i);
							trainers.removeItemAt(i);// remove element from model
							break;
						}
					}
				} catch (MyException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

	}
}
