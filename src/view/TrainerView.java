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
import classes.PokemonExtra;
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
	private DataBattleShowable showable = DataBattleFactory.getDataBattleShowable();
	private LinkedHashMap<String, Pokemon> pc;
	private LinkedHashSet<Pokemon> team;
	private JButton backButton;
	private JPanel panelInfo;
	private Simulable simulable = SimulableFactory.getSimulable();
	private DataBattleShowable dbs = DataBattleFactory.getDataBattleShowable();
	private AccountManageable acc = AccountManageableFactory.getAccountManageable();
	private JComboBox<String> comboPokemon = new JComboBox();
	private JLabel lbl_PokemonName;
	private JButton btn_Escape;
	private JLabel lblRandomLvl;
	private JButton btn_ThrowPokeball;

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
		// Pestaña3
		pestanas.addTab("CAPTURE", panel3);
		panel3.setLayout(null);

		JLabel lb_Lvl = new JLabel("Lvl.");
		lb_Lvl.setForeground(new Color(255, 0, 0));
		lb_Lvl.setFont(new Font("Pokemon Fire Red", Font.BOLD, 25));
		lb_Lvl.setBounds(350, 137, 45, 23);
		panel3.add(lb_Lvl);

		JLabel lbl_PokeballNumber = new JLabel(getPokeballNum());
		lbl_PokeballNumber.setBounds(358, 414, 45, 23);
		panel3.add(lbl_PokeballNumber);

		lblRandomLvl = new JLabel(randomLvl());
		lblRandomLvl.setForeground(new Color(255, 0, 0));
		lblRandomLvl.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
		lblRandomLvl.setBounds(405, 135, 51, 23);
		panel3.add(lblRandomLvl);

		lbl_PokemonName = new JLabel("");
		lbl_PokemonName.setForeground(new Color(0, 0, 0));
		lbl_PokemonName.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 30));
		lbl_PokemonName.setText(randomPokemon());
		lbl_PokemonName.setBounds(214, 117, 163, 58);
		panel3.add(lbl_PokemonName);

		JLabel lblNewLabel_6 = new JLabel("X");
		lblNewLabel_6.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 25));
		lblNewLabel_6.setBounds(336, 391, 33, 70);
		panel3.add(lblNewLabel_6);

		JLabel lbl_Sprite = new JLabel("");
		lbl_Sprite.setIcon(new ImageIcon(TrainerView.class.getResource("/resources/bulbasur.gif")));
		lbl_Sprite.setBounds(251, 175, 152, 165);
		panel3.add(lbl_Sprite);

		JLabel lbl_WildPokémonEncounter = new JLabel("Wild Pokémon encounter!");
		lbl_WildPokémonEncounter.setForeground(new Color(0, 0, 0));
		lbl_WildPokémonEncounter.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 40));
		lbl_WildPokémonEncounter.setBounds(194, 62, 310, 63);
		panel3.add(lbl_WildPokémonEncounter);

		btn_ThrowPokeball = new JButton("THROW POKEBALL");
		btn_ThrowPokeball.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 15));
		btn_ThrowPokeball.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(
				TrainerView.class.getResource("/resources/584-5843757_pokemon-dialog-box-pokemon-text-box-png.png")));
		lblNewLabel_3.setBounds(157, 26, 362, 200);
		panel3.add(lblNewLabel_3);
		btn_ThrowPokeball.setBounds(251, 471, 152, 23);
		panel3.add(btn_ThrowPokeball);

		btn_Escape = new JButton("Run away!");
		btn_Escape.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 15));
		btn_Escape.addActionListener(this);
		btn_Escape.setBounds(567, 498, 86, 23);
		panel3.add(btn_Escape);

		JLabel lbl_Pokeball = new JLabel("");
		lbl_Pokeball.setIcon(new ImageIcon(TrainerView.class.getResource("/resources/Poké_Ball_icon.svg (1).png")));
		lbl_Pokeball.setBounds(256, 391, 70, 70);
		panel3.add(lbl_Pokeball);

		JLabel lbl_Background_1 = new JLabel("");
		lbl_Background_1.setIcon(new ImageIcon(
				TrainerView.class.getResource("/resources/DVMT-6OXcAE2rZY.jpg.afab972f972bd7fbd4253bc7aa1cf27f.jpg")));
		lbl_Background_1.setBounds(-11, -48, 791, 608);
		panel3.add(lbl_Background_1);
		

		// Pestaña4
		pestanas.addTab("SIMULATION", panel4);
		panel4.setLayout(null);

		JLabel lbl_TrainerAgainstName = new JLabel("");
		lbl_TrainerAgainstName.setBounds(424, 306, 99, 23);
		panel4.add(lbl_TrainerAgainstName);

		JLabel lbl_YourName = new JLabel("");
		lbl_YourName.setBounds(125, 267, 89, 23);
		panel4.add(lbl_YourName);

		JLabel lblSelecPKSim = new JLabel("Choose a trainer to battle against");
		lblSelecPKSim.setForeground(new Color(255, 0, 128));
		lblSelecPKSim.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 35));
		lblSelecPKSim.setBounds(23, 33, 377, 32);
		panel4.add(lblSelecPKSim);

		comboPokemon.setBackground(new Color(192, 192, 192));
		comboPokemon.setBounds(74, 75, 235, 32);
		addTrainer();
		comboPokemon.setSelectedIndex(-1);
		comboPokemon.addActionListener(this);
		panel4.add(comboPokemon);

		JButton btnAtacar = new JButton("FIGHT");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAtacar.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 15));
		btnAtacar.setBounds(265, 426, 89, 23);
		btnAtacar.setEnabled(false); // Deshabilitar el botón inicialmente
		panel4.add(btnAtacar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TrainerView.class.getResource("/resources/goldTrainer.png")));
		lblNewLabel.setBounds(92, 277, 152, 128);
		panel4.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TrainerView.class.getResource("/resources/8K8b (1).gif")));
		lblNewLabel_1.setBounds(328, 23, 257, 273);
		panel4.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("VS");
		lblNewLabel_2.setForeground(new Color(204, 0, 0));
		lblNewLabel_2.setFont(new Font("Pokemon Fire Red", Font.PLAIN, 50));
		lblNewLabel_2.setBounds(287, 239, 40, 32);
		panel4.add(lblNewLabel_2);

		JLabel lbl_Setting = new JLabel("");
		lbl_Setting.setIcon(new ImageIcon(TrainerView.class
				.getResource("/resources/d6rqxyw-baf81cc2-f50f-4ab4-8265-c2831654c3f2-2124573794 (1).png")));
		lbl_Setting.setBounds(102, 61, 740, 457);
		panel4.add(lbl_Setting);

		JLabel lbl_Background = new JLabel("");
		lbl_Background.setIcon(new ImageIcon(
				TrainerView.class.getResource("/resources/DVMT-6OXcAE2rZY.jpg.afab972f972bd7fbd4253bc7aa1cf27f.jpg")));
		lbl_Background.setBounds(-95, -63, 832, 658);
		panel4.add(lbl_Background);

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

	private String getPokeballNum() {
		int pokeball = 0;
		try {
			LinkedHashSet<Trainer> trainers = acc.getTrainers();

			for (Trainer p : trainers) {
				pokeball = p.getPokeballs();
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Integer.toString(pokeball);
	}

	private String randomLvl() {
		int lvl = (int) (Math.random() * (100 - 1) + 1);

		return Integer.toString(lvl);
	}

	public void addTrainer() {

		try {
			LinkedHashSet<Trainer> trainers = acc.getTrainers();

			for (Trainer p : trainers) {
				String name = p.getName();
				comboPokemon.addItem(name);
			}
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String randomPokemon() {
		String pokemonName = null;
		try {
			LinkedHashSet<PokemonExtra> pokemons = dbs.getPokemons();
			int rdm = (int) (Math.random() * (pokemons.size() - 1) + 1);
			int i = 1;

			for (PokemonExtra pokemonExtra : pokemons) {

				if (i == rdm) {
					pokemonName = pokemonExtra.getName();
				}

				i++;
			}
		} catch (MyException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}

		return pokemonName;
	}
}