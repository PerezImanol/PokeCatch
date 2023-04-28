package view;

import java.awt.EventQueue;
import factories.LogeableFactory;
import interfaces.Logeable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import classes.MyException;
import classes.Professor;
import classes.Trainer;

import java.awt.SystemColor;

public class LoginView extends JFrame implements ActionListener, FocusListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton goBackButton;
	private JButton continueButton;
	private JLabel errorMessage;
	private Logeable logeable = LogeableFactory.getLogeable();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginView() {

		setTitle("Log in to PokeCatch");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/resources/descarga.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 607);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel initials = new JLabel("");
		initials.setIcon(new ImageIcon(LoginView.class.getResource("/resources/Captura.PNG")));
		initials.setBounds(339, 164, 501, 371);
		contentPane.add(initials);

		JLabel pokeCatch = new JLabel("");
		pokeCatch.setIcon(new ImageIcon(
				LoginView.class.getResource("/resources/PokeCatch-56d0c8a0589d01eea221bdf040d9a9cc.png")));
		pokeCatch.setBounds(30, 0, 744, 139);
		contentPane.add(pokeCatch);

		JLabel welcomeMenssage = new JLabel("Welcome back to PokeCatch !!");
		welcomeMenssage.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 18));
		welcomeMenssage.setBounds(40, 139, 290, 115);
		contentPane.add(welcomeMenssage);

		usernameField = new JTextField();
		usernameField.setForeground(new Color(0, 0, 0));
		usernameField.setBackground(new Color(196, 255, 255));
		usernameField.setBounds(60, 269, 211, 28);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		usernameField.addFocusListener(this);
		usernameField.addKeyListener(this);

		continueButton = new JButton("CONTINUE");
		continueButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		continueButton.setBounds(52, 432, 112, 48);
		contentPane.add(continueButton);
		continueButton.setEnabled(false);
		continueButton.addActionListener(this);

		goBackButton = new JButton("GO BACK");
		goBackButton.setBounds(174, 432, 120, 48);
		contentPane.add(goBackButton);
		goBackButton.addActionListener(this);

		JLabel usernameLabel = new JLabel("USERNAME");
		usernameLabel.setFont(new Font("Yu Gothic Light", Font.BOLD, 17));
		usernameLabel.setBounds(65, 235, 265, 28);
		contentPane.add(usernameLabel);

		passwordField = new JPasswordField();
		passwordField.setBackground(new Color(196, 255, 255));
		passwordField.setBounds(60, 347, 211, 28);
		contentPane.add(passwordField);
		passwordField.addFocusListener(this);
		passwordField.addKeyListener(this);

		JLabel passwordLabel = new JLabel("PASSWORD");
		passwordLabel.setFont(new Font("Yu Gothic Light", Font.BOLD, 17));
		passwordLabel.setBounds(65, 308, 265, 28);
		contentPane.add(passwordLabel);

		errorMessage = new JLabel("Username or password do not belong to a trainer");
		errorMessage.setForeground(new Color(255, 0, 0));
		errorMessage.setFont(new Font("Tahoma", Font.PLAIN, 13));
		errorMessage.setBounds(40, 386, 290, 35);
		contentPane.add(errorMessage);
		errorMessage.setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(SystemColor.activeCaption));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(30, 168, 300, 344);
		contentPane.add(panel);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Trainer t=null;
		
		if (e.getSource().equals(goBackButton)) {
			if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				this.dispose();
			} else {

			}

		}
		if (e.getSource().equals(continueButton)) {
			String password = new String(passwordField.getPassword());
			try {
				t=logeable.getPerson(usernameField.getText(), password);
				if(t instanceof Professor ) {
				System.out.println(t);
				} else if (t instanceof Trainer ) {
				System.out.println("Perro");
				}
				else if(t==null){
					errorMessage.setVisible(true);
					usernameField.setText("");
					passwordField.setText("");
				}
			} catch (MyException e1) {

				e1.printStackTrace();
			}
		}

	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(usernameField)){
			passwordField.setEnabled(true);
		}
	}

	public void focusLost(FocusEvent e) {
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		String password = new String(passwordField.getPassword());
		if(e.getSource().equals(passwordField) && !usernameField.getText().isBlank() || 
				e.getSource().equals(usernameField) && !password.equals("")) {
			continueButton.setEnabled(true);
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource().equals(usernameField)) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				passwordField.requestFocus();
			}
		}
				if (e.getSource().equals(passwordField)) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						continueButton.requestFocus();
					}
				}
			}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}