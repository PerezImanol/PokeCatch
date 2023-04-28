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
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.awt.Window.Type;
import javax.swing.*;
import java.awt.event.*;

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
	private JTextField textField_1;

	public ProfessorView() {
		super();
		setType(Type.POPUP);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("Professor View");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();

		// Creamos el objeto JTabbedPane y le agregamos las pestañas
		JTabbedPane pestanas = new JTabbedPane();

		// Pestaña1
		// Creamos un panel y lo agregamos a la pestaña "BATTLES"
		JPanel panel1 = new JPanel();
		pestanas.addTab("BATTLES", panel1);

		// Establecemos un layout nulo para el panel
		panel1.setLayout(null);

		// Creamos un botón llamado "INFORMATION" y lo agregamos al panel
		JButton btnNewButton = new JButton("INFORMATION");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 164, 122, 26);
		panel1.add(btnNewButton);

		// Creamos una ComboBox y la agregamos al panel, con tres elementos
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("");
		comboBox.addItem("Entrenador 1");
		comboBox.addItem("Entrenador 2");
		comboBox.setBounds(219, 69, 222, 31);
		panel1.add(comboBox);

		// Creamos un botón llamado "TEAM" y lo agregamos al panel
		JButton btnNewButton_1 = new JButton("TEAM");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(279, 164, 105, 26);
		panel1.add(btnNewButton_1);

		// Creamos un botón llamado "COMBAT HISTORY" y lo agregamos al panel
		JButton btnNewButton_1_1 = new JButton("COMBAT HISTORY");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_1.setBounds(503, 164, 150, 26);
		panel1.add(btnNewButton_1_1);

		// Creamos una etiqueta y la agregamos al panel
		JLabel lblNewLabel = new JLabel("Select Trainner");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(80, 74, 105, 17);
		panel1.add(lblNewLabel);

		// Creamos un campo de texto y lo agregamos al panel
		textField = new JTextField();
		textField.setBackground(new Color(255, 128, 192));
		textField.setEditable(false);
		textField.setBounds(10, 297, 643, 132);
		panel1.add(textField);

		// Agregamos un ActionListener a la ComboBox para habilitar los botones después
		// de seleccionar un elemento
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

		// Pestaña2
		JPanel panel23 = new JPanel();
		pestanas.addTab("DELETE", panel2);
		panel2.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Select Trainner");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 76, 105, 17);
		panel2.add(lblNewLabel_1);

		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.addItem("");
		comboBox_1.addItem("Entrenador 1");
		comboBox_1.addItem("Entrenador 2");
		comboBox_1.setBounds(162, 71, 222, 31);
		panel2.add(comboBox_1);

		JButton btnNewButton_2 = new JButton("CHECK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(429, 71, 124, 31);
		panel2.add(btnNewButton_2);

		JButton btnNewButton_2_1 = new JButton("DELETE");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showOptionDialog(
						panel1,
						"Are you suere you want to delete this trainner?",
						"Confirm delete",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						new Object[] { "YES", "NO" },
						JOptionPane.YES_OPTION);
			}
		});

		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton_2.setEnabled(true);
				btnNewButton_2_1.setEnabled(true);
			}
		});

		// Deshabilitamos los botones por defecto
		btnNewButton_2.setEnabled(false);
		btnNewButton_2_1.setEnabled(false);

		btnNewButton_2_1.setBounds(354, 349, 124, 31);
		panel2.add(btnNewButton_2_1);

		// Pestaña3
		textField_1 = new JTextField();
		textField_1.setBounds(10, 189, 237, 351);
		panel2.add(textField_1);
		textField_1.setColumns(10);
		pestanas.addTab("ADD/MODIFY", panel3);
		panel3.setLayout(null);

		JLabel lblNewLabel_3_1 = new JLabel("SET INITIAL");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3_1.setBounds(151, 263, 86, 14);
		panel3.add(lblNewLabel_3_1);

		JButton btnNewButton_3 = new JButton("CHECK");
		btnNewButton_3.setBounds(293, 54, 89, 23);
		panel3.add(btnNewButton_3);

		txtNameAndSurname = new JTextField();
		txtNameAndSurname.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameAndSurname.setText("NAME AND SURNAME");
		txtNameAndSurname.setBounds(40, 55, 193, 20);
		panel3.add(txtNameAndSurname);
		txtNameAndSurname.setColumns(10);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(40, 137, 101, 20);
		panel3.add(dateChooser);

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

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(40, 259, 101, 22);
		panel3.add(comboBox_2);

		txtPokeballs = new JTextField();
		txtPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtPokeballs.setColumns(10);
		txtPokeballs.setBounds(40, 290, 101, 20);
		panel3.add(txtPokeballs);

		txtAddPokeballs = new JTextField();
		txtAddPokeballs.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddPokeballs.setBounds(405, 290, 101, 20);
		panel3.add(txtAddPokeballs);
		txtAddPokeballs.setColumns(10);

		JButton btnNewButton_4 = new JButton("ADD");
		btnNewButton_4.setBounds(516, 288, 74, 23);
		panel3.add(btnNewButton_4);

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

		// Desactivar los componentes relevantes
		dateChooser.setEnabled(false);
		txtGender.setEnabled(false);
		txtOriginCity.setEnabled(false);
		txtBadges.setEnabled(false);
		comboBox_2.setEnabled(false);
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
				comboBox_2.setEnabled(true);
				txtPokeballs.setEnabled(true);
				txtAddPokeballs.setEnabled(true);
				btnNewButton_4.setEnabled(true);
				btnNewButton_4_1.setEnabled(true);
			}
		});

		// Pestaña4
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
		lblNewLabel_4.setBounds(243, 58, 147, 14);
		panel4.add(lblNewLabel_4);

		JComboBox comboBox_1_1 = new JComboBox();
		comboBox_1_1.addItem("");
		comboBox_1_1.addItem("Trainner 1");
		comboBox_1_1.addItem("Trainner 2");
		comboBox_1_1.setBounds(10, 52, 222, 31);
		panel4.add(comboBox_1_1);

		txtRegion = new JTextField();
		txtRegion.setHorizontalAlignment(SwingConstants.CENTER);
		txtRegion.setText("REGION");
		txtRegion.setBounds(27, 131, 139, 20);
		panel4.add(txtRegion);
		txtRegion.setColumns(10);
		txtRegion.setEnabled(false);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(27, 162, 139, 22);
		panel4.add(comboBox_3);
		comboBox_3.setEnabled(false);

		JComboBox comboBox_3_1 = new JComboBox();
		comboBox_3_1.setBounds(27, 195, 139, 22);
		panel4.add(comboBox_3_1);
		comboBox_3_1.setEnabled(false);

		JComboBox comboBox_3_2 = new JComboBox();
		comboBox_3_2.setBounds(27, 228, 139, 22);
		panel4.add(comboBox_3_2);
		comboBox_3_2.setEnabled(false);

		JButton btnNewButton_5 = new JButton("UPGRADE");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(panel4, "(trainner_name) has been successfully upgraded to Professor");
			}
		});
		btnNewButton_5.setBounds(203, 172, 122, 69);
		panel4.add(btnNewButton_5);
		btnNewButton_5.setEnabled(false);

		txttrainnernameHasBeen = new JTextField();
		txttrainnernameHasBeen.setEditable(false);
		txttrainnernameHasBeen.setText("(trainner_name) has been successfully upgraded to Professor");
		txttrainnernameHasBeen.setBounds(335, 196, 316, 20);
		panel4.add(txttrainnernameHasBeen);
		txttrainnernameHasBeen.setColumns(10);

		// Deshabilitar elementos al principio
		txtRegion.setEnabled(false);
		comboBox_3.setEnabled(false);
		comboBox_3_1.setEnabled(false);
		comboBox_3_2.setEnabled(false);
		btnNewButton_5.setEnabled(false);

		// Habilitar elementos cuando se seleccione una opción en comboBox_1_1
		comboBox_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedOption = (String) comboBox_1_1.getSelectedItem();
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
		setVisible(true);
	}

	public static void main(String[] args) {
		new ProfessorView();
	}
}