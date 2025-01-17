package Week10;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MYF04 extends JFrame {

	private JButton[] btn = new JButton[12];
	
	public MYF04()
	{
		this.setTitle("OOP Lecture");
		this.setSize(640, 480);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(4, 3, 5, 5));
		
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton(Integer.toString(i + 1));
			this.add(btn[i]);
		}
		
		this.setVisible(true);
	}
}
