package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

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
import classes.Trainer;
import factories.AccountManageableFactory;
import factories.DataBattleFactory;
import factories.SimulableFactory;
import interfaces.AccountManageable;
import interfaces.DataBattleShowable;
import interfaces.Simulable;

public class TrainerView extends JDialog implements ActionListener, FocusListener {
	private JLabel lblPokePC;
	private JLabel lblPokemonInfo;
	private JButton btnSave;
	private JButton btnPok1;
	private JButton btnPok2;
	private JButton btnPok3;
	private JButton btnPok4;
	private JButton btnPok5;
	private JButton btnPok6;
	private JButton btnLanzarPB;
	private JButton btnHuir;
	private JButton btnShow;
	private JButton btnUpdate;
	private JTextField textUserID;
	private JTextField textName;
	private JTextField textOrigin;
	private JTextField textGender;
	private JTextField textBadges;
	private JTextField textPSalv;
	private JTextField textPKRiv;
	private JTextArea textAreaTrainInfo;
	private JTextArea textAreaPKInfo;
	private JDateChooser ageCalender;
	private JComboBox<String> comboBoxPC;
	private JComboBox<String> comboBoxSelecPK;
	private JComboBox<String> teamPokemonToSwitch;
	private JComboBox<String> pcPokemonToSwitch;
	private JPanel panelPC;
	private JButton btnSwitch;
	private Trainer trainer;
	private AccountManageable manageable = AccountManageableFactory.getAccountManageable();
	private Simulable simulable = SimulableFactory.getSimulable();
	private DataBattleShowable showable = DataBattleFactory.getDataBattleShowable();
	private LinkedHashMap<String, Pokemon> pc;
	private LinkedHashSet<Pokemon> team;
	private JButton backButton;
	private JPanel panelInfo;

	public TrainerView(LoginView loginView, Trainer t) {
		super(loginView, true);
		setResizable(false);
		trainer = t;
		setTitle("Trainer View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginView.dispose();

		panelInfo = new JPanel();
		panelPC = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"
		pestanas.addTab("INFO", panelInfo);
		panelInfo.setLayout(null);

		textUserID = new JTextField();
		textUserID.setEditable(false);
		textUserID.setBounds(27, 108, 96, 19);
		panelInfo.add(textUserID);
		textUserID.setColumns(10);

		textName = new JTextField();
		textName.setColumns(10);
		textName.setBounds(27, 168, 96, 19);
		panelInfo.add(textName);

		textOrigin = new JTextField();
		textOrigin.setBounds(27, 223, 96, 19);
		panelInfo.add(textOrigin);
		textOrigin.setColumns(10);

		textGender = new JTextField();
		textGender.setColumns(10);
		textGender.setBounds(219, 168, 96, 19);
		panelInfo.add(textGender);

		textBadges = new JTextField();
		textBadges.setEditable(false);
		textBadges.setColumns(10);
		textBadges.setBounds(219, 223, 96, 19);
		panelInfo.add(textBadges);

		JLabel lblUserID = new JLabel("USER ID");
		lblUserID.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUserID.setBounds(27, 73, 96, 25);
		panelInfo.add(lblUserID);

		ageCalender = new JDateChooser();
		ageCalender.getCalendarButton();
		ageCalender.setBounds(219, 108, 96, 19);
		panelInfo.add(ageCalender);

		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(27, 133, 96, 25);
		panelInfo.add(lblName);

		JLabel lblOriginCity = new JLabel("ORIGIN CITY");
		lblOriginCity.setHorizontalAlignment(SwingConstants.LEFT);
		lblOriginCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOriginCity.setBounds(27, 197, 110, 25);
		panelInfo.add(lblOriginCity);

		JLabel lblGender = new JLabel("GENDER");
		lblGender.setHorizontalAlignment(SwingConstants.LEFT);
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGender.setBounds(219, 133, 76, 25);
		panelInfo.add(lblGender);

		JLabel lblBadges = new JLabel("BADGES");
		lblBadges.setHorizontalAlignment(SwingConstants.LEFT);
		lblBadges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBadges.setBounds(218, 197, 110, 25);
		panelInfo.add(lblBadges);

		btnSave = new JButton("SAVE");
		btnSave.addActionListener(this);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSave.setBounds(129, 312, 85, 21);
		panelInfo.add(btnSave);

		textAreaTrainInfo = new JTextArea();
		textAreaTrainInfo.setForeground(new Color(0, 0, 0));
		textAreaTrainInfo.setFont(new Font("JetBrains Mono Medium", Font.PLAIN, 14));
		textAreaTrainInfo.setLineWrap(true);
		textAreaTrainInfo.setColumns(10);
		textAreaTrainInfo.setEditable(false);
		textAreaTrainInfo.setBounds(429, 78, 197, 335);
		panelInfo.add(textAreaTrainInfo);

		JLabel lblAge = new JLabel("AGE");
		lblAge.setHorizontalAlignment(SwingConstants.LEFT);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAge.setBounds(219, 73, 76, 25);
		panelInfo.add(lblAge);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(this);
		btnUpdate.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnUpdate.setBounds(454, 450, 148, 21);
		btnUpdate.setVisible(false);
		panelInfo.add(btnUpdate);

		// Pestaña2
		pestanas.addTab("PC", panelPC);
		panelPC.setLayout(null);

		lblPokePC = new JLabel("POKÉMON IN THE PC");
		lblPokePC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokePC.setBounds(29, 56, 153, 21);
		panelPC.add(lblPokePC);

		lblPokemonInfo = new JLabel("POKÉMON INFO");
		lblPokemonInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPokemonInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokemonInfo.setBounds(426, 56, 153, 21);
		panelPC.add(lblPokemonInfo);

		comboBoxPC = new JComboBox<String>();
		comboBoxPC.setBounds(29, 87, 153, 31);
		panelPC.add(comboBoxPC);

		btnPok1 = new JButton("POK1");
		btnPok1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok1.setBounds(29, 171, 114, 21);
		btnPok1.addActionListener(this);
		panelPC.add(btnPok1);

		btnPok2 = new JButton("POK2");
		btnPok2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok2.setBounds(201, 171, 124, 21);
		btnPok2.addActionListener(this);
		panelPC.add(btnPok2);

		btnPok3 = new JButton("POK3");
		btnPok3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok3.setBounds(29, 228, 114, 21);
		btnPok3.addActionListener(this);
		panelPC.add(btnPok3);

		btnPok4 = new JButton("POK4");
		btnPok4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok4.setBounds(201, 228, 124, 21);
		btnPok4.addActionListener(this);
		panelPC.add(btnPok4);

		btnPok5 = new JButton("POK5");
		btnPok5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok5.setBounds(29, 282, 114, 21);
		btnPok5.addActionListener(this);
		panelPC.add(btnPok5);

		btnPok6 = new JButton("POK6");
		btnPok6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPok6.setBounds(201, 282, 124, 21);
		btnPok6.addActionListener(this);
		panelPC.add(btnPok6);

		textAreaPKInfo = new JTextArea();
		textAreaPKInfo.setLineWrap(true);
		textAreaPKInfo.setColumns(10);
		textAreaPKInfo.setEditable(false);
		textAreaPKInfo.setBounds(407, 89, 206, 326);
		panelPC.add(textAreaPKInfo);

		btnShow = new JButton("SHOW");
		btnShow.addActionListener(this);
		btnShow.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnShow.setBounds(204, 90, 99, 21);
		panelPC.add(btnShow);

		teamPokemonToSwitch = new JComboBox<String>();
		teamPokemonToSwitch.setSelectedIndex(-1);
		teamPokemonToSwitch.setBounds(150, 239, 153, 31);
		teamPokemonToSwitch.setVisible(false);
		panelPC.add(teamPokemonToSwitch);

		pcPokemonToSwitch = new JComboBox<String>();
		pcPokemonToSwitch.setSelectedIndex(-1);
		pcPokemonToSwitch.setBounds(398, 239, 153, 31);
		pcPokemonToSwitch.setVisible(false);
		panelPC.add(pcPokemonToSwitch);

		btnSwitch = new JButton("SWITCH MENU");
		btnSwitch.addActionListener(this);
		btnSwitch.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnSwitch.setBounds(105, 341, 153, 21);
		panelPC.add(btnSwitch);

		backButton = new JButton("BACK");
		backButton.setVerticalAlignment(SwingConstants.BOTTOM);
		backButton.setFont(new Font("Dialog", Font.PLAIN, 14));
		backButton.setBounds(41, 457, 114, 21);
		backButton.addActionListener(this);
		backButton.setVisible(false);
		panelPC.add(backButton);

		// Agregar ActionListener a la JComboBox
		comboBoxPC.addActionListener(this);

		// Pestaña3
		pestanas.addTab("CAPTURE", panel3);
		panel3.setLayout(null);

		JLabel lblSelecPKCap = new JLabel("Seleccione Pokémon");
		lblSelecPKCap.setBounds(21, 122, 109, 14);
		panel3.add(lblSelecPKCap);

		JLabel lblPKSalv = new JLabel("Pokémon salvaje");
		lblPKSalv.setBounds(432, 94, 86, 14);
		panel3.add(lblPKSalv);

		comboBoxSelecPK = new JComboBox<String>();
		comboBoxSelecPK.setBounds(140, 118, 118, 22);
		panel3.add(comboBoxSelecPK);

		textPSalv = new JTextField();
		textPSalv.setEditable(false);
		textPSalv.setBounds(432, 119, 86, 20);
		panel3.add(textPSalv);
		textPSalv.setColumns(10);

		btnLanzarPB = new JButton("LANZAR POKEBALL");
		btnLanzarPB.setBounds(140, 232, 152, 23);
		btnLanzarPB.setEnabled(false); // Deshabilitar el botón inicialmente
		panel3.add(btnLanzarPB);

		btnHuir = new JButton("HUIR");
		btnHuir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnHuir.setBounds(400, 232, 86, 23);
		btnHuir.setEnabled(false); // Deshabilitar el botón inicialmente
		panel3.add(btnHuir);

		comboBoxSelecPK.addActionListener(this);

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

		textPKRiv = new JTextField();
		textPKRiv.setBounds(378, 119, 86, 20);
		panel4.add(textPKRiv);
		textPKRiv.setColumns(10);

		// Agregamos el objeto JTabbedPane a la ventana
		getContentPane().add(pestanas);
		setPokemonButtonsAndComboBox();
		setPersonalInfo();

		// Configuramos la ventana
		setSize(682, 614);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnPok1) || e.getSource().equals(btnPok2) || e.getSource().equals(btnPok3)
				|| e.getSource().equals(btnPok4) || e.getSource().equals(btnPok5) || e.getSource().equals(btnPok6)) {

			JButton aux = (JButton) e.getSource();

			for (Pokemon p : trainer.getTeam()) {
				if (p.getName().equals(aux.getText())) {
					textAreaPKInfo.setText(p.toString());
				}
			}
			if (aux.getText() == "")
				textAreaPKInfo.setText("");
		}

		if (e.getSource().equals(btnShow)) {
			textAreaPKInfo.setText(
					comboBoxPC.getSelectedIndex() != -1 ? pc.get(comboBoxPC.getSelectedItem()).toString() : " ");
		}

		if (e.getSource().equals(btnSwitch)) {
			if (btnSwitch.getText().equalsIgnoreCase("SWITCH MENU")) {
				toggleSwitchMode(true);

				teamPokemonToSwitch.removeAllItems();
				pcPokemonToSwitch.removeAllItems();

				for (Pokemon p : team) {
					teamPokemonToSwitch.addItem(p.getName());
				}
				for (String name : pc.keySet()) {
					pcPokemonToSwitch.addItem(name);
				}

				teamPokemonToSwitch.setSelectedIndex(-1);
				pcPokemonToSwitch.setSelectedIndex(-1);

			} else if (btnSwitch.getText().equalsIgnoreCase("SWITCH")) {
				if (teamPokemonToSwitch.getSelectedIndex() != -1 && pcPokemonToSwitch.getSelectedIndex() != -1) {
					if (JOptionPane.showOptionDialog(panelPC, "Are you sure you want to switch these pokemon?",
							"Confirm switch", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
							new Object[] { "YES", "NO" }, JOptionPane.YES_OPTION) == 0) {
						Iterator<Pokemon> it = team.iterator();
						boolean found = false;
						while (it.hasNext() && !found) {
							Pokemon p = it.next();
							if (teamPokemonToSwitch.getSelectedItem().equals(p.getName())) {
								try {
									simulable.switchPosition(trainer, p, pc.get(pcPokemonToSwitch.getSelectedItem()));
								} catch (Exception er) {
									JOptionPane.showMessageDialog(null, er.getMessage(), "WARNING",
											JOptionPane.INFORMATION_MESSAGE);
								} finally {
									switchInternally(p, pc.get(pcPokemonToSwitch.getSelectedItem()));
								}
								found = true;
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Select 2 pokemons first", "WARNING",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		if (e.getSource().equals(backButton)) {
			toggleSwitchMode(false);
			trainer.setTeam(team);
			setPokemonButtonsAndComboBox();
		}

		if (e.getSource().equals(btnSave)) {
			updateTrainer();
			textAreaTrainInfo.setText(trainer.getTrainerInfo());
			btnUpdate.setVisible(true);
		}
		if (e.getSource().equals(btnUpdate)) {
			try {
				manageable.modifyTrainer(trainer);
			} catch (MyException er) {
				JOptionPane.showMessageDialog(null, er.getMessage(), "WARNING", JOptionPane.INFORMATION_MESSAGE);
			} finally {
				btnUpdate.setVisible(false);
			}
		}
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		throw new UnsupportedOperationException("Unimplemented method 'focusGained'");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		throw new UnsupportedOperationException("Unimplemented method 'focusLost'");
	}

	private void setPokemonButtonsAndComboBox() {
		team = trainer.getTeam();
		try {
			pc = showable.getPcPokemons(trainer.getTrainerID());
		} catch (MyException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}

		Pokemon teamStatic[] = new Pokemon[6];
		team.toArray(teamStatic);

		btnPok1.setText(teamStatic[0] == null ? "" : teamStatic[0].getName());
		btnPok2.setText(teamStatic[1] == null ? "" : teamStatic[1].getName());
		btnPok3.setText(teamStatic[2] == null ? "" : teamStatic[2].getName());
		btnPok4.setText(teamStatic[3] == null ? "" : teamStatic[3].getName());
		btnPok5.setText(teamStatic[4] == null ? "" : teamStatic[4].getName());
		btnPok6.setText(teamStatic[5] == null ? "" : teamStatic[5].getName());

		comboBoxPC.removeAllItems();

		for (String name : pc.keySet()) {
			comboBoxPC.addItem(name);
		}
		comboBoxPC.setSelectedIndex(-1);
	}

	private void toggleSwitchMode(Boolean flag) {
		if (flag) {
			btnSwitch.setBounds(265, 299, 153, 21);
			btnSwitch.setText("SWITCH");
		} else {
			btnSwitch.setBounds(105, 341, 153, 21);
			btnSwitch.setText("SWITCH MENU");
		}

		teamPokemonToSwitch.setVisible(flag);
		pcPokemonToSwitch.setVisible(flag);
		backButton.setVisible(flag);

		lblPokePC.setVisible(!flag);
		lblPokemonInfo.setVisible(!flag);
		comboBoxPC.setVisible(!flag);
		btnShow.setVisible(!flag);
		textAreaPKInfo.setVisible(!flag);
		btnPok1.setVisible(!flag);
		btnPok2.setVisible(!flag);
		btnPok3.setVisible(!flag);
		btnPok4.setVisible(!flag);
		btnPok5.setVisible(!flag);
		btnPok6.setVisible(!flag);
	}

	private void switchInternally(Pokemon teamPokemon, Pokemon pcPokemon) {
		team.remove(teamPokemon);
		team.add(pcPokemon);

		pc.remove(pcPokemon.getName());
		pc.put(teamPokemon.getName(), teamPokemon);
	}

	private void setPersonalInfo() {
		textUserID.setText(String.valueOf(trainer.getTrainerID()));
		textName.setText(trainer.getName());
		textOrigin.setText(trainer.getOriginCity());
		ageCalender.setDate(trainer.getBirthdate());
		textGender.setText(trainer.getGender());
		textBadges.setText(String.valueOf(trainer.getBadges()));
	}

	private void updateTrainer() {
		java.sql.Date date = new java.sql.Date(ageCalender.getDate().toInstant().toEpochMilli());

		trainer.setName(textName.getText());
		trainer.setBirthdate(date);
		trainer.setGender(textGender.getText());
		trainer.setOriginCity(textOrigin.getText());
		trainer.setBadges(Integer.parseInt(textBadges.getText()));

	}
}