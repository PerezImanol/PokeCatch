package view;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Panel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class ProfessorView extends JDialog {
	private JTextField txtNameAndSurname;
	private JTextField txtGender;
	private JTextField txtOriginCity;
	private JTextField txtBadges;
	private JTextField txtPokeballs;
	private JTextField txtAddPokeballs;
	private JTextField txtRegion;
	private JTextField txttrainnernameHasBeen;
	private JTextField textField;

	public ProfessorView() {
		super();
		setTitle("Professor View");
		setModalityType(ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Creamos las cuatro pestañas
		JPanel panel1 = new JPanel();
		pestanas.addTab("BATTLES", panel1);
		panel1.setLayout(null);

		JButton btnNewButton = new JButton("INFORMATION");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(panel1, "Información seleccionada");
		    }
		});
		btnNewButton.setBounds(10, 164, 122, 26);
		panel1.add(btnNewButton);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("");
		comboBox.addItem("Entrenador 1");
		comboBox.addItem("Entrenador 2");
		comboBox.setBounds(219, 69, 222, 31);
		panel1.add(comboBox);

		JButton btnNewButton_1 = new JButton("TEAM");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(panel1, "Equipo seleccionado");
		    }
		});
		btnNewButton_1.setBounds(279, 164, 105, 26);
		panel1.add(btnNewButton_1);

		JButton btnNewButton_1_1 = new JButton("COMBAT HISTORY");
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JOptionPane.showMessageDialog(panel1, "Historial de combate seleccionado");
		    }
		});
		btnNewButton_1_1.setBounds(503, 164, 150, 26);
		panel1.add(btnNewButton_1_1);

		JLabel lblNewLabel = new JLabel("Select Trainner");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(80, 74, 105, 17);
		panel1.add(lblNewLabel);

		textField = new JTextField();
		textField.setBackground(new Color(255, 0, 0));
		textField.setEditable(false);
		textField.setBounds(10, 297, 643, 132);
		panel1.add(textField);

		// Agregamos un ActionListener a la ComboBox para habilitar los botones después de seleccionar un elemento
		comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        btnNewButton.setEnabled(true);
		        btnNewButton_1.setEnabled(true);
		        btnNewButton_1_1.setEnabled(true);
		    }
		});

		// Deshabilitamos los botones por defecto
		btnNewButton.setEnabled(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_1_1.setEnabled(false);

		// Segunda pestaña
		JPanel panel23 = new JPanel();
		pestanas.addTab("DELETE", panel2);
		panel2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Select Trainner");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 76, 105, 17);
		panel2.add(lblNewLabel_1);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("Entrenador 1");
		comboBox_1.addItem("Entrenador 2");
		comboBox_1.setBounds(162, 71, 222, 31);
		panel2.add(comboBox_1);


		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(429, 71, 124, 31);
		panel2.add(btnNewButton_2);

		JLabel lblNewLabel_2 = new JLabel("ARE YOU SUERE YOU WANT TO DELETE THIS TRAINNER?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(95, 178, 458, 20);
		panel2.add(lblNewLabel_2);

		JButton btnNewButton_2_1 = new JButton("DELETE");
		btnNewButton_2_1.setBounds(238, 243, 124, 31);
		panel2.add(btnNewButton_2_1);
		pestanas.addTab("ADD/MODIFY", panel3);
		panel3.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("SET INITIAL");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setBounds(151, 263, 86, 14);
		panel3.add(lblNewLabel_3_1);

		txtNameAndSurname = new JTextField();
		txtNameAndSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameAndSurname.setText("NAME AND SURNAME");
		txtNameAndSurname.setBounds(40, 55, 193, 20);
		panel3.add(txtNameAndSurname);
		txtNameAndSurname.setColumns(10);

		JButton btnNewButton_3 = new JButton("CHECK");
		btnNewButton_3.setBounds(293, 54, 89, 23);
		panel3.add(btnNewButton_3);

		txtGender = new JTextField();
		txtGender.setHorizontalAlignment(SwingConstants.CENTER);
		txtGender.setBounds(40, 166, 101, 20);
		panel3.add(txtGender);
		txtGender.setColumns(10);

		txtOriginCity = new JTextField();
		txtOriginCity.setHorizontalAlignment(SwingConstants.CENTER);
		txtOriginCity.setColumns(10);
		txtOriginCity.setBounds(40, 197, 101, 20);
		panel3.add(txtOriginCity);

		txtBadges = new JTextField();
		txtBadges.setHorizontalAlignment(SwingConstants.CENTER);
		txtBadges.setColumns(10);
		txtBadges.setBounds(40, 228, 101, 20);
		panel3.add(txtBadges);

		txtPokeballs = new JTextField();
		txtPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPokeballs.setColumns(10);
		txtPokeballs.setBounds(40, 290, 101, 20);
		panel3.add(txtPokeballs);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(40, 259, 101, 22);
		panel3.add(comboBox_2);

		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setBounds(40, 133, 101, 22);
		panel3.add(comboBox_2_1);

		txtAddPokeballs = new JTextField();
		txtAddPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddPokeballs.setBounds(405, 290, 101, 20);
		panel3.add(txtAddPokeballs);
		txtAddPokeballs.setColumns(10);

		JButton btnNewButton_4 = new JButton("ADD");
		btnNewButton_4.setBounds(516, 288, 74, 23);
		panel3.add(btnNewButton_4);

		JButton btnNewButton_4_1 = new JButton("");
		btnNewButton_4_1.setBounds(261, 448, 74, 23);
		panel3.add(btnNewButton_4_1);

		JLabel lblNewLabel_3 = new JLabel("AGE");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(151, 137, 86, 14);
		panel3.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_2 = new JLabel("GENDER");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2.setBounds(151, 168, 86, 14);
		panel3.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("ORIGIN CITY");
		lblNewLabel_3_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_1.setBounds(151, 199, 86, 14);
		panel3.add(lblNewLabel_3_2_1);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("BADGES");
		lblNewLabel_3_2_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_2.setBounds(151, 230, 86, 14);
		panel3.add(lblNewLabel_3_2_2);
		
		JLabel lblNewLabel_3_2_2_1 = new JLabel("POKEBALLS");
		lblNewLabel_3_2_2_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_2_2_1.setBounds(151, 292, 86, 14);
		panel3.add(lblNewLabel_3_2_2_1);
		pestanas.addTab("ASCEND", panel4);
		panel4.setLayout(null);

		JLabel lblNewLabel_5_1_1 = new JLabel("LEAF INITIAL");
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setBounds(27, 232, 139, 14);
		panel4.add(lblNewLabel_5_1_1);

		JLabel lblNewLabel_5_1 = new JLabel("FIRE INITIAL");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setBounds(27, 199, 139, 14);
		panel4.add(lblNewLabel_5_1);

		JLabel lblNewLabel_5 = new JLabel("WATER INITIAL");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(27, 166, 139, 14);
		panel4.add(lblNewLabel_5);

		JLabel lblNewLabel_4 = new JLabel("Select Trainner");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(10, 60, 222, 14);
		panel4.add(lblNewLabel_4);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(10, 52, 222, 31);
		panel4.add(comboBox_1_1);

		txtRegion = new JTextField();
		txtRegion.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegion.setText("REGION");
		txtRegion.setBounds(27, 131, 139, 20);
		panel4.add(txtRegion);
		txtRegion.setColumns(10);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(27, 162, 139, 22);
		panel4.add(comboBox_3);

		JComboBox comboBox_3_1 = new JComboBox();
		comboBox_3_1.setBounds(27, 195, 139, 22);
		panel4.add(comboBox_3_1);

		JComboBox comboBox_3_2 = new JComboBox();
		comboBox_3_2.setBounds(27, 228, 139, 22);
		panel4.add(comboBox_3_2);

		JButton btnNewButton_5 = new JButton("UPGRADE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_5.setBounds(203, 172, 122, 69);
		panel4.add(btnNewButton_5);

		txttrainnernameHasBeen = new JTextField();
		txttrainnernameHasBeen.setEditable(false);
		txttrainnernameHasBeen.setText("(trainner_name) has been successfully upgraded to Professor");
		txttrainnernameHasBeen.setBounds(335, 196, 316, 20);
		panel4.add(txttrainnernameHasBeen);
		txttrainnernameHasBeen.setColumns(10);

		// Agregamos el objeto JTabbedPane a la ventana
		getContentPane().add(pestanas);
		
		Panel panel = new Panel();
		pestanas.addTab("New tab", null, panel, null);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("318px"),
				ColumnSpec.decode("85px"),},
			new RowSpec[] {
				RowSpec.decode("262px"),
				RowSpec.decode("21px"),}));
		
		JButton btnNewButton_6 = new JButton("New button");
		panel.add(btnNewButton_6, "2, 2, left, top");

		// Configuramos la ventana
		setSize(682, 614);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ProfessorView();
	}
}
