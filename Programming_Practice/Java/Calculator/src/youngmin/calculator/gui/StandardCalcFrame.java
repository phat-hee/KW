package youngmin.calculator.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.*;

import youngmin.util.Utility;

@SuppressWarnings("serial")
public class StandardCalcFrame extends BaseFrame 
{
	public static final int WINDOW_SIZE_WIDTH = 640;
	public static final int WINDOW_SIZE_HEIGHT = 480;
	
	private JPanel buttonPanel, resultPanel;
	
	private JMenuBar menuBar;
	private JMenu kindMenu;
	
	private JButton[] numberButton;
	private JLabel resultLabel;
	
	private StringBuilder expSB;

	// Test
	private JTextField inputField;
	
	private final String[] arrButtonStr = { "CE", "C", "��", "��",
											"7", "8", "9", "��",
											"4", "5", "6", "-",
											"1", "2", "3", "+",
											"��", "0", ".", "="};
	
	public StandardCalcFrame()
	{	
		super.setFrame(WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT, "Youngmin's Calculator", JFrame.DISPOSE_ON_CLOSE);
	
		expSB = new StringBuilder();
	}
	
	@Override
	public void setComponent()
	{
		menuBar = new JMenuBar();
		kindMenu = new JMenu("Kind");
			
		resultPanel = new JPanel();
		resultLabel = new JLabel("0");
		
		buttonPanel = new JPanel(new GridLayout(5, 4, 2, 2));
		numberButton = new JButton[arrButtonStr.length];
				
		inputField = new JTextField(30);
	}
	
	@Override
	public void design() {
		// TODO Auto-generated method stub
		kindMenu.add(new JMenuItem("Standard"));
		kindMenu.add(new JMenuItem("Scientific"));
		kindMenu.add(new JMenuItem("Programmer"));
		kindMenu.add(new JMenuItem("Date"));
		kindMenu.addSeparator();
		kindMenu.add(new JMenuItem("Exit"));
		menuBar.add(kindMenu);
		super.setJMenuBar(menuBar);
		
		resultLabel.setFont(new Font("�߰���", Font.BOLD, 25));
		resultPanel.add(resultLabel);
		super.add("North", resultPanel);
		
		for (int i = 0; i < numberButton.length; i++)
		{
			numberButton[i] = new JButton(arrButtonStr[i]);
			numberButton[i].setFont(new Font("�߰���", Font.BOLD, 30));
			buttonPanel.add(numberButton[i]);
		}
		super.add("Center", buttonPanel);

		super.add("South", inputField);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		for (int i = 0; i < numberButton.length; i++)
		{
			numberButton[i].addActionListener(new Input());
		}
	}
	
	class Input implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (Utility.isOperator(e.getActionCommand()))
			{
				expSB.append(e.getActionCommand());
				resultLabel.setText(expSB.toString());
			}
			else if (e.getActionCommand().equals("CE") ||
					e.getActionCommand().equals("��"))
			{
				expSB.setLength(expSB.length() - 1);
				
				if (expSB.length() != 0)
					resultLabel.setText(expSB.toString());
				else
					resultLabel.setText("0");
			}
			else if (e.getActionCommand().equals("C"))
			{
				expSB.setLength(0);
				resultLabel.setText("0");
			}
			else if (e.getActionCommand().equals("="))
			{
				String regex = "+|-|��|��";
				StringTokenizer expToken = new StringTokenizer(expSB.toString(), regex, true);
				int preOperand, postOperand;
				int result = 0;
				String operator;
				
				preOperand = Integer.parseInt(expToken.nextToken());
				operator = expToken.nextToken();
				postOperand = Integer.parseInt(expToken.nextToken());
				
				switch (operator)
				{
				case "+":
					result = preOperand + postOperand;
					break;
				case "-":
					result = preOperand - postOperand;
					break;
				case "��":
					result = preOperand * postOperand;
					break;
				case "��":
					result = preOperand / postOperand;
					break;
				}
				expSB.replace(0, expSB.length(), Integer.toString(result));
				resultLabel.setText(expSB.toString());
			}
		}
	}
}
