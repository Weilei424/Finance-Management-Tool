package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DB.DBUtil;
import persistence.LedgerItem;
import persistence.User;

public class InvestmentPageForm implements ActionListener {

	public JFrame investmentPageFrame;
	private JPanel investmentPageForm;
	private JLabel investmentName;
	public JTextField investmentNameInput;
	private JLabel investmentCost;
	public JTextField investmentCostInput;
	// JLabel expenseCategory; *No real code for this yet
	private JLabel investmentDescription;
	public JTextField earningDescriptionInput;
	private JLabel earningDate;
	public JTextField earningDateInput;
	private JButton submit;
	private LedgerItem ledgerItem;
	private InvestmentPage ep;
	private int framesCreated;

	public InvestmentPageForm() {
		this.framesCreated = 0;

		investmentPageFrame = new JFrame();
		investmentPageForm = new JPanel();

		investmentPageForm.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		investmentPageForm.setLayout(new GridLayout(5, 1));
		investmentPageForm.setBackground(Color.cyan);

		investmentName = new JLabel("Name of Investment:");
		investmentName.setSize(100, 20);
		investmentName.setLocation(100, 100);
		investmentPageForm.add(investmentName);

		investmentNameInput = new JTextField();
		investmentNameInput.setSize(100, 20);
		investmentNameInput.setLocation(300, 100);
		investmentPageForm.add(investmentNameInput);

		investmentCost = new JLabel("Amount of Investment:");
		investmentCost.setSize(100, 20);
		investmentCost.setLocation(100, 200);
		investmentPageForm.add(investmentCost);

		investmentCostInput = new JTextField();
		investmentCostInput.setSize(100, 20);
		investmentCostInput.setLocation(200, 200);
		investmentPageForm.add(investmentCostInput);

		investmentDescription = new JLabel("Description:");
		investmentDescription.setSize(100, 20);
		investmentDescription.setLocation(100, 300);
		investmentPageForm.add(investmentDescription);

		earningDescriptionInput = new JTextField();
		earningDescriptionInput.setSize(100, 20);
		earningDescriptionInput.setLocation(200, 300);
		investmentPageForm.add(earningDescriptionInput);

		earningDate = new JLabel("Date:");
		earningDate.setSize(100, 20);
		earningDate.setLocation(100, 400);
		investmentPageForm.add(earningDate);

		earningDateInput = new JTextField();
		earningDateInput.setSize(100, 20);
		earningDateInput.setLocation(200, 400);
		investmentPageForm.add(earningDateInput);

		submit = new JButton("Submit");
		submit.setBounds(20, 10, 100, 50);
		submit.addActionListener(this);
		investmentPageForm.add(submit);

		// Adding the expense form panel into the main frame
		investmentPageFrame.add(investmentPageForm, BorderLayout.CENTER);
		investmentPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		investmentPageFrame.setTitle("Add Investment");
		investmentPageFrame.setSize(400, 300);
		// expensePageFrame.pack(); // when setSize on, then remove pack
		investmentPageFrame.setVisible(true);

	}

	public JTextField getInvestmentNameInput() {
		return investmentNameInput;
	}

	public JTextField getInvestmentCostInput() {
		return investmentCostInput;
	}

	public JTextField getInvestmentDescriptionInput() {
		return earningDescriptionInput;
	}

	public JTextField getInvestmentDateInput() {
		return earningDateInput;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.framesCreated < 1) {
			ep = new InvestmentPage();
			ep.mainEpFrame.setVisible(true);
			ep.getAddInvestment().setVisible(false);
			
		}

		String expName = investmentNameInput.getText();
		String expNote = earningDescriptionInput.getText();
		String expDate = earningDateInput.getText();
		double expCost = Double.parseDouble(investmentCostInput.getText());

		this.ledgerItem = new LedgerItem(expDate, expCost, expName, expNote);

		DBUtil.insert(User.getLoginAs(), this.ledgerItem, "investment");

		ep.setTempLedgerItem(this.ledgerItem);
		ep.setNumberOfInvestment(ep.getNumberOfInvestment() + 1);

		try {
			ep.investmentTable = DBUtil.query(User.getLoginAs(),"tag","investment");
			ep.mainEpFrame.dispose();
			ep = new InvestmentPage();
			ep.mainEpFrame.setVisible(true);
			ep.getAddInvestment().setVisible(false);
			investmentPageFrame.dispose();
			} catch(SQLException er) {
				
			}
		this.framesCreated++;
	}

	public LedgerItem getLedgerItem() {
		return ledgerItem;
	}

	public static void main(String[] args) {
		new InvestmentPageForm();
	}

}