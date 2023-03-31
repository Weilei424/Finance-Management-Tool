package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import DB.DBUtil;
import persistence.LedgerItem;
import persistence.User;

public class CardPursePageForm implements ActionListener{	

		public JFrame cardPurseFrame;
		private JPanel cardPurseForm;
		private JLabel cardName;
		public JTextField cardNameInput;
		private JLabel cardCost;
		public JTextField cardCostInput;
		private JLabel cardDescription;
		public JTextField cardDescriptionInput;
		private JLabel cardDate;
		public JTextField cardDateInput;
		private JButton submit;
		private LedgerItem ledgerItem;
		private CardPursePage cpp;
		private int framesCreated;

		public CardPursePageForm() {
			this.framesCreated = 0;

			cardPurseFrame = new JFrame();
			cardPurseFrame.setLocationRelativeTo(null);
			cardPurseForm = new JPanel();

			cardPurseForm.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
			cardPurseForm.setLayout(new GridLayout(5, 1));
			cardPurseForm.setBackground(Color.cyan);

			cardName = new JLabel("Name of Card:");
			cardName.setSize(100, 20);
			cardName.setLocation(100, 100);
			cardPurseForm.add(cardName);

			cardNameInput = new JTextField();
			cardNameInput.setSize(100, 20);
			cardNameInput.setLocation(300, 100);
			cardPurseForm.add(cardNameInput);

			cardCost = new JLabel("Card Balance:");
			cardCost.setSize(100, 20);
			cardCost.setLocation(100, 200);
			cardPurseForm.add(cardCost);

			cardCostInput = new JTextField();
			cardCostInput.setSize(100, 20);
			cardCostInput.setLocation(200, 200);
			cardPurseForm.add(cardCostInput);

			cardDescription = new JLabel("Description of Card:");
			cardDescription.setSize(100, 20);
			cardDescription.setLocation(100, 300);
			cardPurseForm.add(cardDescription);

			cardDescriptionInput = new JTextField();
			cardDescriptionInput.setSize(100, 20);
			cardDescriptionInput.setLocation(200, 300);
			cardPurseForm.add(cardDescriptionInput);

			cardDate = new JLabel("Date of payment:");
			cardDate.setSize(100, 20);
			cardDate.setLocation(100, 400);
			cardPurseForm.add(cardDate);

			cardDateInput = new JTextField();
			cardDateInput.setSize(100, 20);
			cardDateInput.setLocation(200, 400);
			cardPurseForm.add(cardDateInput);

			submit = new JButton("Submit");
			submit.setBounds(20, 10, 100, 50);
			submit.addActionListener(this);
			cardPurseForm.add(submit);

			// Adding the expense form panel into the main frame
			cardPurseFrame.add(cardPurseForm, BorderLayout.CENTER);
			cardPurseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			cardPurseFrame.setTitle("Add Cards");
			cardPurseFrame.setSize(400, 300);
			cardPurseFrame.setVisible(true);

		}

		public JTextField getCardNameInput() {
			return cardNameInput;
		}

		public JTextField getCardCostInput() {
			return cardCostInput;
		}

		public JTextField getCardDescriptionInput() {
			return cardDescriptionInput;
		}

		public JTextField getCardDateInput() {
			return cardDateInput;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			if (cardNameInput.getText().isEmpty() || cardCostInput.getText().isEmpty() || cardDateInput.getText().isEmpty()) {
				JOptionPane.showMessageDialog(cardPurseFrame, "Please enter the name, balance, and date.");
				return;
			}

			if (this.framesCreated < 1) {
				cpp = new CardPursePage();
				cpp.mainCpPage.setVisible(true);
				cpp.getAddNewCard().setVisible(false);
				
			}

			String expName = cardNameInput.getText();
			String expNote = cardDescriptionInput.getText();
			String expDate = cardDateInput.getText();
			double expCost = Double.parseDouble(cardCostInput.getText());

			this.ledgerItem = new LedgerItem(expDate, expCost, expName, expNote);

			DBUtil.insert(User.getLoginAs(), this.ledgerItem, "card");

		

			try {
				cpp.cardPurseTable = DBUtil.query(User.getLoginAs(),"tag","card");
				cpp.mainCpPage.dispose();
				cpp = new CardPursePage();
				cpp.mainCpPage.setVisible(true);
				cpp.getAddNewCard().setVisible(false);
				} catch(SQLException er) {
					
				}
			this.framesCreated++;
		}

		public LedgerItem getLedgerItem() {
			return ledgerItem;
		}

		public static void main(String[] args) {
			new CardPursePageForm();
		}
}
