package gui ;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import com.edu.noticeapp.repository.NoticeDAO;
import com.edu.noticeapp.domain.Notice;

class RegistForm extends JFrame {
	JTextField t_title;
	JTextField t_writer;
	JTextField t_content;
	JButton bt_regist;
	NoticeDAO noticeDAO;

	public RegistForm(){
		t_title=new JTextField();
		t_writer=new JTextField();
		t_content=new JTextField();
		noticeDAO=new NoticeDAO();

		t_title.setPreferredSize(new Dimension(380,30));
		t_writer.setPreferredSize(new Dimension(380,30));
		t_content.setPreferredSize(new Dimension(380,30));
		bt_regist=new JButton("등록");

		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(t_content);
		add(bt_regist);

		setSize(400,300);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//버튼에 리스너 연결
		bt_regist.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				regist();
			}	
		});

	}
	public void regist(){
		//empty dto
		Notice notice=new Notice();
		notice.setTitle(t_title.getText());//제목
		notice.setWriter(t_writer.getText()); //작성자
		notice.setContent(t_content.getText()); //내용

		int result=noticeDAO.insert(notice);
		if(result>0){
			JOptionPane.showMessageDialog(this,"등록성공");
		}else{
			JOptionPane.showMessageDialog(this,"등록실패");
		}
	}

	public static void main(String[] args) {
		new RegistForm();
	}
}
