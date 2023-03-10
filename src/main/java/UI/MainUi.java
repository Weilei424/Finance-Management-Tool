package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import persistence.DBUtil;

public class MainUi implements ActionListener {

	// Global Variables used for main UI

	public JFrame mainFrame;
	private JPanel mainPanel;
	private JButton userLogIn;
	private JLabel welcomeText;
	private JLabel userText;
	private JTextField userTextInput;
	private JLabel passwordText;
	private JPasswordField userPasswordInput;
	private ExpensePage mainExpensePage;

	// The constructor holds all the initialization of every global variable and
	// where to use it
	public MainUi() {

		// Initializing all variables
		mainFrame = new JFrame();
		mainPanel = new JPanel();
		mainExpensePage = new ExpensePage();
		mainExpensePage.mainEpFrame.setVisible(false);
		userLogIn = new JButton("Login!");
		welcomeText = new JLabel("Welcome to Wallet Watcher!", SwingConstants.CENTER);
		userText = new JLabel("Username");
		userTextInput = new JTextField();
		passwordText = new JLabel("Password");
		userPasswordInput = new JPasswordField();

		// Setting the bounds and the action listener (unused as of right now) for the
		// login button
		userLogIn.setBounds(0, 80, 80, 25);
		userLogIn.addActionListener(this);

		// Setting the bounds for the texts and user inputs
		userText.setBounds(10, 20, 80, 25);
		userTextInput.setBounds(30, 20, 80, 25);
		passwordText.setBounds(10, 50, 80, 25);
		userPasswordInput.setBounds(30, 50, 80, 25);

		// Creating the main panel holding all the information for the login page and
		// setting background colour
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		mainPanel.setLayout(new GridLayout(0, 1));
		mainPanel.add(welcomeText);
		mainPanel.add(userText);
		mainPanel.add(userTextInput);
		mainPanel.add(passwordText);
		mainPanel.add(userPasswordInput);
		mainPanel.add(userLogIn);
		mainPanel.setBackground(Color.GREEN);

		// Adding it all to the main frame to be visible in UI
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("Wallet Watcher");
		mainFrame.setSize(450, 350);
		// mainFrame.pack();
		mainFrame.setVisible(true);

	}

	// Action Listener for the button to be able to navigate to another page when
	// clicked if user name and password is correct
	@Override
	public void actionPerformed(ActionEvent e) {

		String uName = userTextInput.getText();
		String uPass = userPasswordInput.getText();

		if (DBUtil.validateUser(uName, uPass)) {
			mainExpensePage.mainEpFrame.setVisible(true);
			mainFrame.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(mainFrame, "Sorry, not a valid username or password");
		}

	}

}
