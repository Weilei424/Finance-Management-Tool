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

public class EarningPageForm implements ActionListener {

	public JFrame earningPageFrame;
	private JPanel earningPageForm;
	private JLabel earningName;
	public JTextField earningNameInput;
	private JLabel earningCost;
	public JTextField earningCostInput;
	// JLabel expenseCategory; *No real code for this yet
	private JLabel earningDescription;
	public JTextField earningDescriptionInput;
	private JLabel earningDate;
	public JTextField earningDateInput;
	private JButton submit;
	private LedgerItem ledgerItem;
	private EarningPage ep;
	private int framesCreated;

	public EarningPageForm() {
		this.framesCreated = 0;

		earningPageFrame = new JFrame();
		earningPageForm = new JPanel();

		earningPageForm.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		earningPageForm.setLayout(new GridLayout(5, 1));
		earningPageForm.setBackground(Color.cyan);

		earningName = new JLabel("Name of Earning:");
		earningName.setSize(100, 20);
		earningName.setLocation(100, 100);
		earningPageForm.add(earningName);

		earningNameInput = new JTextField();
		earningNameInput.setSize(100, 20);
		earningNameInput.setLocation(300, 100);
		earningPageForm.add(earningNameInput);

		earningCost = new JLabel("Amount of Earning:");
		earningCost.setSize(100, 20);
		earningCost.setLocation(100, 200);
		earningPageForm.add(earningCost);

		earningCostInput = new JTextField();
		earningCostInput.setSize(100, 20);
		earningCostInput.setLocation(200, 200);
		earningPageForm.add(earningCostInput);

		earningDescription = new JLabel("Description of Earning:");
		earningDescription.setSize(100, 20);
		earningDescription.setLocation(100, 300);
		earningPageForm.add(earningDescription);

		earningDescriptionInput = new JTextField();
		earningDescriptionInput.setSize(100, 20);
		earningDescriptionInput.setLocation(200, 300);
		earningPageForm.add(earningDescriptionInput);

		earningDate = new JLabel("Date:");
		earningDate.setSize(100, 20);
		earningDate.setLocation(100, 400);
		earningPageForm.add(earningDate);

		earningDateInput = new JTextField();
		earningDateInput.setSize(100, 20);
		earningDateInput.setLocation(200, 400);
		earningPageForm.add(earningDateInput);

		submit = new JButton("Submit");
		submit.setBounds(20, 10, 100, 50);
		submit.addActionListener(this);
		earningPageForm.add(submit);

		// Adding the expense form panel into the main frame
		earningPageFrame.add(earningPageForm, BorderLayout.CENTER);
		earningPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		earningPageFrame.setTitle("Add Earning");
		earningPageFrame.setSize(400, 300);
		// expensePageFrame.pack(); // when setSize on, then remove pack
		earningPageFrame.setVisible(true);

	}

	public JTextField getEarningNameInput() {
		return earningNameInput;
	}

	public JTextField getEarningCostInput() {
		return earningCostInput;
	}

	public JTextField getEarningDescriptionInput() {
		return earningDescriptionInput;
	}

	public JTextField getEarningDateInput() {
		return earningDateInput;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (this.framesCreated < 1) {
			ep = new EarningPage();
			ep.mainEpFrame.setVisible(true);
			ep.getAddEarning().setVisible(false);
			
		}

		String expName = earningNameInput.getText();
		String expNote = earningDescriptionInput.getText();
		String expDate = earningDateInput.getText();
		double expCost = Double.parseDouble(earningCostInput.getText());

		this.ledgerItem = new LedgerItem(expDate, expCost, expName, expNote);

		DBUtil.insert(User.getLoginAs(), this.ledgerItem, "budget");

		ep.setTempLedgerItem(this.ledgerItem);
		ep.setNumberOfEarning(ep.getNumberOfEarning() + 1);

		try {
			ep.earningTable = DBUtil.query(User.getLoginAs(),"tag","expense");
			ep.mainEpFrame.dispose();
			ep = new EarningPage();
			ep.mainEpFrame.setVisible(true);
			ep.getAddEarning().setVisible(false);
			earningPageFrame.dispose();
			} catch(SQLException er) {
				
			}
		this.framesCreated++;
	}

	public LedgerItem getLedgerItem() {
		return ledgerItem;
	}

	public static void main(String[] args) {
		new BudgetPageForm();
	}

}