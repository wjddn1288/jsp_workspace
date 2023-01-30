package mvc.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import mvc.model.blood.BloodAdvisor;

public class BloodMain extends JFrame {
	JComboBox<String> box;
	JButton bt;
	BloodAdvisor advisor;
	
	public BloodMain() {
		box= new JComboBox<String>();
		bt= new JButton("분석요청");
		advisor= new BloodAdvisor();
		
		//혈액형 초기화
		box.addItem("A");
		box.addItem("B");
		box.addItem("O");
		box.addItem("AB");
		
		setLayout(new FlowLayout());
		add(box);
		add(bt);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300,400);
		
		bt.addActionListener((e)->{ //코드를 짧게짤려고 람다식
			getAdvice();
		});
	}
	
	public void getAdvice() {
		String result=advisor.getAdvice((String)box.getSelectedItem());//item이 오브젝트라 형변환
		
		JOptionPane.showMessageDialog(this, result);
	}
	
	public static void main(String[] args) {
		new BloodMain();
	}
}
