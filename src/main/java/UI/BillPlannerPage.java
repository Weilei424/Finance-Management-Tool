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
import javax.swing.JPanel;
import javax.swing.JTextArea;

import persistence.LedgerItem;

public class BillPlannerPage implements ActionListener{
	
	public JFrame mainBpPage;
	private JPanel mainBpPanel;
	private JPanel mainPanel; // main panel to hold all other panels in the expense page form
	// JPanel ledgerPanel;
	private JButton addNewBill;
	private JButton removeBill;
	private JLabel title;
	private JTextArea billInfo;
	private ExpensePageForm epForm;
	private LedgerItem tempLedgerItem;

	public BillPlannerPage() {
		mainBpPage = new JFrame();
		mainBpPanel = new JPanel();
		epForm = new ExpensePageForm();
		epForm.expensePageFrame.setVisible(false);
		this.tempLedgerItem = tempLedgerItem;

		// Initialize main title on page, along with initializing button and layouts
		title = new JLabel("Upcoming Bills");
		title.setSize(30, 30);
		title.setFont(new Font("Tahoma", Font.BOLD, 60));

		addNewBill = new JButton("Add Bill");
		addNewBill.setSize(40, 40);
		addNewBill.addActionListener(this);

		// This panel holds the top elements including the title and the ability to add
		// another button
		mainBpPanel.setLayout(new GridLayout(1, 2));
		mainBpPanel.add(title);
		mainBpPanel.add(addNewBill);
		mainBpPanel.setBackground(Color.green);

		// This is the text area which shows all of the "ledger" information

		billInfo = new JTextArea();
		billInfo.append("Bill Name" + "\t" + "\t");
		billInfo.append("Bill Charge" + "\t" + "\t");
		billInfo.append("Date Due" + "\t" + "\t");
		billInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		// Disables user from being able to add any text into area as it is only for
		// displaying the ledger
		billInfo.setEditable(false);

		removeBill = new JButton(new AbstractAction("Remove All Expenses") {

			@Override
			public void actionPerformed(ActionEvent e) {

					billInfo.setText(null);
					billInfo.append("Bill Name" + "\t");
					billInfo.append("Bill Charge" + "\t");
					billInfo.append("Date Due" + "\t" + "\t" + "\t");
				 
			}

		});
		removeBill.setForeground(Color.green);

		// This panel holds all other elements in the frame
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainPanel.add(mainBpPanel);
		mainPanel.setBackground(Color.green);

		// This is the main frame which holds the main panel and all other elements
		// enclosed in it
		mainBpPage.add(mainPanel, BorderLayout.NORTH);
		mainBpPage.add(billInfo, BorderLayout.CENTER);
		mainBpPage.add(removeBill, BorderLayout.SOUTH);
		mainBpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainBpPage.setTitle("Expenses");
		mainBpPage.setSize(1000, 1000);
		// expensePageFrame.pack(); // when setSize on, then remove pack
		mainBpPage.setVisible(true);

}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		new BillPlannerPage();
	}
}
