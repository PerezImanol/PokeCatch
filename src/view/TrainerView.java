package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.util.Iterator;
import javax.swing.JTextField;
import javax.swing.*;

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
import java.awt.Color;

public class TrainerView extends JDialog implements ActionListener, FocusListener {
	private JButton btnSave;
	private JButton btnPok1;
	private JButton btnPok2;
	private JButton btnPok3;
	private JButton btnPok4;
	private JButton btnPok5;
	private JButton btnPok6;
	private JButton btnLanzarPB;
	private JButton btnHuir;
	private JTextField textUser;
	private JTextField textPassword;
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
	private Trainer trainer;
	private AccountManageable manageable= AccountManageableFactory.getAccountManageable();
	private Simulable simulable = SimulableFactory.getSimulable();
	private DataBattleShowable showable = DataBattleFactory.getDataBattleShowable();

	public TrainerView(LoginView loginView, Trainer t) {
		super(loginView, true);
		setResizable(false);
		trainer = t;
		setTitle("Trainer View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		loginView.dispose();

		try {
			LinkedHashMap<String, Pokemon> pokemonsInPc = showable.getPcPokemons(trainer.getTrainerID());

		} catch (MyException er ){
			JOptionPane.showMessageDialog(null, er.getMessage(), "WARNING", JOptionPane.ERROR_MESSAGE);
		}

		JPanel panelInfo = new JPanel();
		JPanel panelPC = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"
		pestanas.addTab("INFO", panelInfo);
		panelInfo.setLayout(null);

		textUser = new JTextField();
		textUser.setBounds(27, 108, 96, 19);
		panelInfo.add(textUser);
		textUser.setColumns(10);

		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(27, 168, 96, 19);
		panelInfo.add(textPassword);

		textOrigin = new JTextField();
		textOrigin.setBounds(27, 223, 96, 19);
		panelInfo.add(textOrigin);
		textOrigin.setColumns(10);

		textGender = new JTextField();
		textGender.setColumns(10);
		textGender.setBounds(219, 168, 96, 19);
		panelInfo.add(textGender);

		textBadges = new JTextField();
		textBadges.setColumns(10);
		textBadges.setBounds(219, 223, 96, 19);
		panelInfo.add(textBadges);

		JLabel lblUser = new JLabel("USER");
		lblUser.setHorizontalAlignment(SwingConstants.LEFT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(27, 73, 76, 25);
		panelInfo.add(lblUser);

		ageCalender = new JDateChooser();
		ageCalender.setBounds(219, 108, 96, 19);
		panelInfo.add(ageCalender);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(27, 133, 76, 25);
		panelInfo.add(lblPassword);

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

		// Pestaña2
		pestanas.addTab("PC", panelPC);
		panelPC.setLayout(null);

		JLabel lblPokePC = new JLabel("POKÉMON IN THE PC");
		lblPokePC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokePC.setBounds(29, 64, 153, 13);
		panelPC.add(lblPokePC);

		JLabel lblPokemonInfo = new JLabel("POKÉMON INFO");
		lblPokemonInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblPokemonInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPokemonInfo.setBounds(426, 64, 153, 13);
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
		
		JButton btnShow = new JButton("SHOW");
		btnShow.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnShow.setBounds(204, 90, 99, 21);
		panelPC.add(btnShow);

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

		textPKRiv = new JTextField();
		textPKRiv.setBounds(378, 119, 86, 20);
		panel4.add(textPKRiv);
		textPKRiv.setColumns(10);

		// Agregamos el objeto JTabbedPane a la ventana
		getContentPane().add(pestanas);

		setPokemonButtons();

		// Configuramos la ventana
		setSize(682, 614);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource().equals(btnPok1) || e.getSource().equals(btnPok2) || e.getSource().equals(btnPok3) 
		||e.getSource().equals(btnPok4) || e.getSource().equals(btnPok5) || e.getSource().equals(btnPok6)){

			JButton aux = (JButton) e.getSource();

			for (Pokemon p : trainer.getTeam()){
				if (p.getName().equals(aux.getText())){
					textAreaPKInfo.setText(p.toString());
				}
			}
			if (aux.getText() == "")
				textAreaPKInfo.setText("");
		}
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'focusGained'");
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'focusLost'");
	}

	private void setPokemonButtons(){
		LinkedHashSet<Pokemon> t = trainer. getTeam();
		Pokemon team [] = new Pokemon[6];
		t.toArray(team);

			btnPok1.setText(team[0] == null ? "" : team[0].getName());
			btnPok2.setText(team[1] == null ? "" : team[1].getName());
			btnPok3.setText(team[2] == null ? "" : team[2].getName());
			btnPok4.setText(team[3] == null ? "" : team[3].getName());
			btnPok5.setText(team[4] == null ? "" : team[4].getName());
			btnPok6.setText(team[5] == null ? "" : team[5].getName());

	}
}