package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;

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
import classes.Pokemon;
import classes.Professor;
import classes.Trainer;
import factories.AccountManageableFactory;
import interfaces.AccountManageable;

public class ProfessorView extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField addCheckField;
	private JTextField addGenderField;
	private JTextField addCityField;
	private JTextField addBadgesField;
	private JTextField addPokeballsField;
	private JTextField pokeballAdderField;
	private JTextField txtRegion;
	private JTextField txttrainnernameHasBeen;
	private JTextArea infotoaddField;
	private JTextArea resulttextField;
	private JTextArea deleteTrainerTextArea;
	private JButton teamButton;
	private JButton combatHistoryButton;
	private JButton informationButton;
	private JButton checkButton;
	private JButton deleteButton;
	private JButton addCheckBtn;
	private JButton pokeballAdderBtn;
	private JButton addTrainerButton;
	private JButton addValidateBtn;
	private JDateChooser addCalender;
	private JComboBox<String> Trainers;
	private JComboBox<String> addInitalsComboBox;
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
	private Professor prof;
	private LinkedHashSet<Trainer> trainers;

	public ProfessorView(LoginView loginView, boolean b, Professor loggedProf) {
		super(loginView, b);
		prof = loggedProf;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ProfessorView.class.getResource("/resources/descarga.png")));
		setTitle("Professor View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginView.dispose();

		panelBattle = new JPanel();
		paneDelete = new JPanel();
		panelModify = new JPanel();
		panelAscend = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"

		panelBattle.setBackground(new Color(255, 255, 255));
		pestanas.addTab("BATTLES", panelBattle);

		// Establecemos un layout nulo para el panel
		panelBattle.setLayout(null);

		// Creamos una ComboBox y la agregamos al panel, con tres elementos


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

		JLabel lblNewLabel_1 = new JLabel("Which trainer do you want to delete?");
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Light", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_1.setBounds(27, 26, 337, 17);
		paneDelete.add(lblNewLabel_1);


		checkButton = new JButton("CHECK");
		checkButton.setBounds(37, 136, 173, 47);
		paneDelete.add(checkButton);
		checkButton.addActionListener(this);

		deleteButton = new JButton("DELETE");
		deleteButton.addActionListener(this);

		// Deshabilitamos los botones por defecto
		checkButton.setEnabled(false);
		deleteButton.setEnabled(false);

		deleteButton.setBounds(37, 201, 173, 47);
		paneDelete.add(deleteButton);

		// Pestaña3
		deleteTrainerTextArea = new JTextArea();
		deleteTrainerTextArea.setBackground(SystemColor.inactiveCaption);
		deleteTrainerTextArea.setBounds(37, 259, 285, 239);
		paneDelete.add(deleteTrainerTextArea);
		deleteTrainerTextArea.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ProfessorView.class.getResource("/resources/Ash_Ketchum_Journeys.png")));
		lblNewLabel.setBounds(415, 26, 216, 472);
		paneDelete.add(lblNewLabel);
		pestanas.addTab("ADD/MODIFY", panelModify);
		panelModify.setLayout(null);

		JLabel intitalsLbl = new JLabel("SET INITIAL");
		intitalsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		intitalsLbl.setBounds(151, 263, 86, 14);
		panelModify.add(intitalsLbl);

		addCheckBtn = new JButton("CHECK");
		addCheckBtn.setBounds(293, 54, 89, 23);
		panelModify.add(addCheckBtn);

		addCheckField = new JTextField();
		addCheckField.setHorizontalAlignment(SwingConstants.CENTER);
		addCheckField.setBounds(40, 55, 193, 20);
		panelModify.add(addCheckField);
		addCheckField.setColumns(10);

		addCalender = new JDateChooser();
		addCalender.setDateFormatString("yyyy-MM-dd");
		addCalender.setBounds(40, 137, 101, 20);
		panelModify.add(addCalender);

		addGenderField = new JTextField();
		addGenderField.setHorizontalAlignment(SwingConstants.CENTER);
		addGenderField.setBounds(40, 166, 101, 20);
		panelModify.add(addGenderField);
		addGenderField.setColumns(10);

		addCityField = new JTextField();
		addCityField.setHorizontalAlignment(SwingConstants.CENTER);
		addCityField.setColumns(10);
		addCityField.setBounds(40, 197, 101, 20);
		panelModify.add(addCityField);

		addBadgesField = new JTextField();
		addBadgesField.setHorizontalAlignment(SwingConstants.CENTER);
		addBadgesField.setColumns(10);
		addBadgesField.setBounds(40, 228, 101, 20);
		panelModify.add(addBadgesField);

		addInitalsComboBox = new JComboBox<String>();
		addInitalsComboBox.setBounds(40, 259, 101, 22);
		panelModify.add(addInitalsComboBox);

		addPokeballsField = new JTextField();
		addPokeballsField.setEditable(false);
		addPokeballsField.setHorizontalAlignment(SwingConstants.CENTER);
		addPokeballsField.setColumns(10);
		addPokeballsField.setBounds(40, 290, 101, 20);
		panelModify.add(addPokeballsField);

		pokeballAdderField = new JTextField();
		pokeballAdderField.setHorizontalAlignment(SwingConstants.CENTER);
		pokeballAdderField.setBounds(255, 290, 52, 20);
		panelModify.add(pokeballAdderField);
		pokeballAdderField.setColumns(10);

		pokeballAdderBtn = new JButton("ADD");
		pokeballAdderBtn.setBounds(336, 288, 74, 23);
		panelModify.add(pokeballAdderBtn);

		pokeballAdderBtn.addActionListener(this);

		addValidateBtn = new JButton("VALIDATE");
		addValidateBtn.addActionListener(this);
		addValidateBtn.setBounds(151, 362, 86, 23);
		panelModify.add(addValidateBtn);

		JLabel ageLbl = new JLabel("AGE");
		ageLbl.setHorizontalAlignment(SwingConstants.LEFT);
		ageLbl.setBounds(151, 137, 86, 14);
		panelModify.add(ageLbl);

		JLabel genderLbl = new JLabel("GENDER");
		genderLbl.setHorizontalAlignment(SwingConstants.LEFT);
		genderLbl.setBounds(151, 168, 86, 14);
		panelModify.add(genderLbl);

		JLabel cityLbl = new JLabel("ORIGIN CITY");
		cityLbl.setHorizontalAlignment(SwingConstants.LEFT);
		cityLbl.setBounds(151, 199, 86, 14);
		panelModify.add(cityLbl);

		JLabel badgesLbl = new JLabel("BADGES");
		badgesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		badgesLbl.setBounds(151, 230, 86, 14);
		panelModify.add(badgesLbl);

		JLabel pokeballLbl = new JLabel("POKEBALLS");
		pokeballLbl.setHorizontalAlignment(SwingConstants.LEFT);
		pokeballLbl.setBounds(151, 292, 86, 14);
		panelModify.add(pokeballLbl);

		// Desactivar los componentes relevantes
		addCalender.setEnabled(false);
		addGenderField.setEnabled(false);
		addCityField.setEnabled(false);
		addBadgesField.setEnabled(false);
		addInitalsComboBox.setEnabled(false);
		addPokeballsField.setEnabled(false);
		pokeballAdderField.setEnabled(false);
		pokeballAdderBtn.setEnabled(false);

		infotoaddField = new JTextArea();
		infotoaddField.setEditable(false);
		infotoaddField.setColumns(10);
		infotoaddField.setBackground(Color.GRAY);
		infotoaddField.setBounds(422, 107, 250, 220);
		infotoaddField.setVisible(false);
		panelModify.add(infotoaddField);

		addTrainerButton = new JButton("ADD");
		addTrainerButton.addActionListener(this);
		addTrainerButton.setBounds(519, 362, 86, 23);
		addTrainerButton.setVisible(false);
		panelModify.add(addTrainerButton);
		// Agregar ActionListener al botón btnNewButton_3
		addCheckBtn.addActionListener(this);

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

		try {
			trainers = getTrainersAndSetComboBoxes();
		} catch (MyException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}
		// Configuramos la ventana
		setSize(682, 614);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(Trainers)) {
			try {
				getTrainers();
			} catch (MyException ex) {
				ex.getMessage();
			}
		}

		if (e.getSource().equals(informationButton)) {
			if (t.getName().equals(Trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getTrainerInfo());

			}
		}
		if (e.getSource().equals(combatHistoryButton)) {
			if (t.getName().equals(Trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getCombats());

			}
		}
		if (e.getSource().equals(teamButton)) {
			if (t.getName().equals(Trainers.getSelectedItem())) {
				resulttextField.setText(null);
				resulttextField.setText(t.getTeamMembers());

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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource().equals(checkButton)) {
			if (t.getName().equals(trainerDelete.getSelectedItem())) {
				deleteTrainerTextArea.setText(null);
				deleteTrainerTextArea.setText(t.getTrainerInfo());

			}
		}
		if (e.getSource().equals(deleteButton)) {
			if (JOptionPane.showOptionDialog(panelBattle, "Are you suere you want to delete this trainner?",
					"Confirm delete", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
					new Object[] { "YES", "NO" }, JOptionPane.YES_OPTION) == 1) {
				int id;
				id = t.getTrainerID();
				try {
					manageable.deleteTrainer(id);
				} catch (MyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		if (e.getSource().equals(addCheckBtn)) {
			ArrayList<Pokemon> initialSelection = prof.getInitialSelection();
			Trainer aux = new Trainer();
			try {
				aux = trainerExists(addCheckField.getText());
				if (aux != null) {
					addCalender.setDate(aux.getBirthdate());
					addGenderField.setText(aux.getGender());
					addCityField.setText(aux.getOriginCity());
					addBadgesField.setText(String.valueOf(aux.getBadges()));
					addPokeballsField.setText(String.valueOf(aux.getPokeballs()));
					addTrainerButton.setText("MODIFY");
				} else {
					setAddEnabled();
					addInitalsComboBox.setEnabled(true);
					for (Pokemon p : initialSelection) {
						addInitalsComboBox.addItem(p.getName());
					}
				}
			} catch (MyException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "WARNING", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (e.getSource().equals(pokeballAdderBtn)) {
			// Obtener valores de txtPokeballs y txtAddPokeballs
			int pokeballs;
			try {
				pokeballs = Integer.parseInt(addPokeballsField.getText());
				try {
					int addPokeballs = Integer.parseInt(pokeballAdderField.getText());
					addPokeballsField.setText(Integer.toString(pokeballs + addPokeballs));
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "Only integers allowed", "WARNING",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (NumberFormatException e1) {
				addPokeballsField.setText(Integer.toString(0));
			}
			// Sumar valores y establecer resultado en txtPokeballs
		}
		if (e.getSource().equals(addValidateBtn)) {
			Trainer aux = buildTrainer();

			showTrainerInfo(aux);
		}
		if (e.getSource().equals(addTrainerButton)) {
			t = buildTrainer();
			try {
				if (addTrainerButton.getText().equals("ADD")) {
					manageable.addTrainer(t);
				} else {
					manageable.modifyTrainer(t);
				}
			} catch (MyException er) {
				JOptionPane.showMessageDialog(null, er.getMessage(), "Message", JOptionPane.INFORMATION_MESSAGE);
			} finally {
				try {
					trainers = getTrainersAndSetComboBoxes();
				} catch (MyException er2) {
					JOptionPane.showMessageDialog(null, er2.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private LinkedHashSet<Trainer> getTrainersAndSetComboBoxes() throws MyException {
		LinkedHashSet<Trainer> t = manageable.getTrainers();

		trainerDelete = new JComboBox<String>();
		trainerDelete.addItem("");
		trainerDelete.setBounds(37, 63, 222, 47);
		paneDelete.add(trainerDelete);
		trainerDelete.addActionListener(this);

		Trainers = new JComboBox<String>();
		Trainers.setMaximumRowCount(20);
		Trainers.setForeground(new Color(0, 0, 0));
		Trainers.setBackground(SystemColor.control);
		Trainers.addItem("");
		Trainers.setBounds(175, 61, 237, 53);
		panelBattle.add(Trainers);
		Trainers.addActionListener(this);

		for (Trainer element : t) {
			trainerDelete.addItem(element.getName());
			Trainers.addItem(element.getName());
		}
		return t;
	}

	private void getTrainers() throws MyException {
		resulttextField.setText("");
		informationButton.setEnabled(true);
		teamButton.setEnabled(true);
		combatHistoryButton.setEnabled(true);
		String selectedTrainer = (String) Trainers.getSelectedItem();
		for (Trainer element : manageable.getTrainers()) {
			if (element.getName().equals(selectedTrainer)) {
				t = element;
			}
		}
	}

	private Trainer trainerExists(String name) throws MyException {
		trainers = manageable.getTrainers();
		Iterator<Trainer> it = trainers.iterator();
		boolean found = false;

		while (it.hasNext() && !found) {
			Trainer aux = it.next();
			if (name.equalsIgnoreCase(aux.getName())) {
				setAddEnabled();

				t = aux;
				found = true;
			}
		}
		if (!found) {
			JOptionPane.showMessageDialog(null, "Trainer not found, you may now add a new one", "WARNING",
					JOptionPane.INFORMATION_MESSAGE);
		}
		return t;
	}

	private void setAddEnabled() {
		addCalender.setEnabled(true);
		addGenderField.setEnabled(true);
		addCityField.setEnabled(true);
		addBadgesField.setEnabled(true);
		addPokeballsField.setEnabled(true);
		pokeballAdderField.setEnabled(true);
		pokeballAdderBtn.setEnabled(true);
	}

	private void showTrainerInfo(Trainer aux) {
		infotoaddField.setVisible(true);
		addTrainerButton.setVisible(true);

		infotoaddField.setText(aux.getTrainerInfo());
	}

	private Trainer buildTrainer() {
		Trainer aux = new Trainer();
		java.sql.Date date = new java.sql.Date(addCalender.getDate().toInstant().toEpochMilli());
		aux.setName(addCheckField.getText());

		for (Trainer a : trainers) {
			if (a.getName().equalsIgnoreCase(aux.getName()))
				aux.setTrainerID(a.getTrainerID());
		}
		aux.setName(addCheckField.getText());
		aux.setBirthdate(date);
		aux.setGender(addGenderField.getText());
		aux.setOriginCity(addCityField.getText());
		aux.setBadges(Integer.parseInt(addBadgesField.getText()));
		aux.setPokeballs(Integer.parseInt(addPokeballsField.getText()));
		aux.setInitial(prof.getInitial((String) addInitalsComboBox.getSelectedItem()));

		return aux;
	}
}