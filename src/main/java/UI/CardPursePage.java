package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import persistence.LedgerItem;

public class CardPursePage implements ActionListener {
	
	public JFrame mainCpPage;
	private JPanel mainCpPanel;
	private JPanel mainPanel; // main panel to hold all other panels in the expense page form
	// JPanel ledgerPanel;
	private JButton addNewCard;
	private JButton removeCard;
	private JLabel title;
	private JTextArea cardInfo;
	private ExpensePageForm epForm;
	private LedgerItem tempLedgerItem;

	public CardPursePage() {
		mainCpPage = new JFrame();
		mainCpPanel = new JPanel();
		epForm = new ExpensePageForm();
		epForm.expensePageFrame.setVisible(false);
		this.tempLedgerItem = tempLedgerItem;

		// Initialize main title on page, along with initializing button and layouts
		title = new JLabel("User Cards");
		title.setSize(30, 30);
		title.setFont(new Font("Tahoma", Font.BOLD, 60));

		addNewCard = new JButton("Add New Card");
		addNewCard.setSize(40, 40);
		addNewCard.addActionListener(this);

		// This panel holds the top elements including the title and the ability to add
		// another button
		mainCpPanel.setLayout(new GridLayout(1, 2));
		mainCpPanel.add(title);
		mainCpPanel.add(addNewCard);
		mainCpPanel.setBackground(Color.green);

		// This is the text area which shows all of the "ledger" information

		cardInfo = new JTextArea();
		cardInfo.append("Card Name" + "\t" + "\t");
		cardInfo.append("Name On Card" + "\t" + "\t");
		cardInfo.append("Card Balance" + "\t" + "\t");
		cardInfo.append("Card Type");
		cardInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// Disables user from being able to add any text into area as it is only for
		// displaying the ledger
		cardInfo.setEditable(false);

		removeCard = new JButton(new AbstractAction("Remove All Expenses") {

			@Override
			public void actionPerformed(ActionEvent e) {

					cardInfo.setText(null);
					cardInfo.append("Card Name" + "\t");
					cardInfo.append("Name On Card" + "\t");
					cardInfo.append("Card Balance" + "\t" + "\t" + "\t");
					cardInfo.append("Card Type");
				 
			}

		});
		removeCard.setForeground(Color.green);

		// This panel holds all other elements in the frame
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.add(mainCpPanel);
		mainPanel.setBackground(Color.green);

		// This is the main frame which holds the main panel and all other elements
		// enclosed in it
		mainCpPage.add(mainPanel, BorderLayout.NORTH);
		mainCpPage.add(cardInfo, BorderLayout.CENTER);
		mainCpPage.add(removeCard, BorderLayout.SOUTH);
		mainCpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainCpPage.setTitle("Expenses");
		mainCpPage.setSize(1000, 1000);
		// expensePageFrame.pack(); // when setSize on, then remove pack
		mainCpPage.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new CardPursePage();
	}

}
