package gui.view;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class JoinForm extends JFrame {
	JTextField t_id;
	JTextField t_name;
	JTextField t_email;
	JButton bt_regist;
	FlowLayout layout;
	
	public JoinForm() {
		t_id = new JTextField(20);
		t_name = new JTextField(20);
		t_email = new JTextField(20);
		bt_regist = new JButton("등록");
		layout= new FlowLayout();
		
		//조립
		setLayout(layout);
		add(t_id);
		add(t_name);
		add(t_email);
		add(bt_regist);
		
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new JoinForm();
	}
}
