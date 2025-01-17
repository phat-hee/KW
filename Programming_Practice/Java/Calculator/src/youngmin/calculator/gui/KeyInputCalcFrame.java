package youngmin.calculator.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import youngmin.calculator.Calculate;

@SuppressWarnings("serial")
public class KeyInputCalcFrame extends BaseFrame {

	private Calculate calculator;
	
	// Test
	private JLabel postfixDescLabel;
	private JLabel postfixLabel;
	
	private JLabel resultDescLabel;
	private JLabel resultLabel;
	private JTextField inputField;
	private JButton inputButton;
	
	public KeyInputCalcFrame()
	{
		super.setFrame(640, 480, "Select", JFrame.DISPOSE_ON_CLOSE);
		
		calculator = new Calculate();
	}
	
	@Override
	public void setComponent() {
		// TODO Auto-generated method stub
		super.setLayout(new GridLayout(6, 2));
		
		postfixDescLabel = new JLabel();
		postfixLabel = new JLabel();
		resultDescLabel = new JLabel();
		resultLabel = new JLabel();

		inputField = new JTextField("계산식을 입력하세요.");
		inputButton = new JButton("입력 완료.");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub		
		Font f = new Font("굴림", Font.BOLD, 30);
		
		postfixDescLabel.setText("Postfix : ");
		resultDescLabel.setText("Result : ");
		
		postfixLabel.setFont(f);
		resultLabel.setFont(f);
		
		super.add(inputField);
		super.add(postfixDescLabel);
		super.add(postfixLabel);
		super.add(resultDescLabel);
		super.add(resultLabel);
		super.add(inputButton);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		inputField.addKeyListener(new Event());
		inputField.addFocusListener(new Event());
		inputButton.addActionListener(new Event());
	}

	class Event implements KeyListener, ActionListener, FocusListener
	{
		@Override
		public void keyPressed(KeyEvent arg0) {}

		@Override
		public void keyReleased(KeyEvent keyArg) {
			// TODO Auto-generated method stub
			if (keyArg.getKeyCode() == KeyEvent.VK_ENTER)
			{
				String postfixExp = calculator.getPostfixExpression(inputField.getText());
				int result = calculator.calculateInt(postfixExp);
				postfixLabel.setText(postfixExp);
				resultLabel.setText(Integer.toString(result));
				
				inputField.setText("");
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) {}

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			inputField.setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			inputField.setText("계산식을 입력하세요.");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String postfixExp = calculator.getPostfixExpression(inputField.getText());
			int result = calculator.calculateInt(postfixExp);
			postfixLabel.setText(postfixExp);
			resultLabel.setText(Integer.toString(result));
			
			inputField.setText("");
		}
		
	}
}
