package Week10;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class MYF01 extends JFrame {
	
	private JButton loginBtn, logoutBtn;
	
	public MYF01() 
	{
		this.setTitle("OOP Lecture");
		this.setSize(640, 480);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setLayout(new FlowLayout());
		
		loginBtn = new JButton("Login");
		this.add(loginBtn);
		
		logoutBtn = new JButton("Logout");
		this.add(logoutBtn);
		
		this.setVisible(true);
	}
}
