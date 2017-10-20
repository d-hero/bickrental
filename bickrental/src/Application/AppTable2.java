package Application;

import java.awt.EventQueue;

import javax.naming.ldap.Rdn;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;


import com.zrgj.bickrental.dao.StationDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Toolkit;

public class AppTable2 {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	
	private JRadioButton rdbtnNewRadioButton;
	StationDao dao=new StationDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppTable2 window = new AppTable2();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public AppTable2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AppTable2.class.getResource("/image/logo.png")));
		frame.setTitle("站点管理");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.RED);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("调度申请表");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.ORANGE);
		lblNewLabel.setBounds(141, 10, 127, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("申请站点号：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(25, 43, 96, 19);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("申请数量：");
		label.setForeground(Color.RED);
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(25, 93, 96, 19);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("申请类型：");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(25, 161, 96, 19);
		frame.getContentPane().add(label_1);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int anum=Integer.valueOf(textField_1.getText());
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				
				if(rdbtnNewRadioButton.isSelected()){
					try {
						System.out.println(time);
						dao.insertTable(textField.getText(),anum,"调入",time);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					try {
						dao.insertTable(textField.getText(),anum,"调出",time);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null,"插入成功！");
			}
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBounds(52, 229, 127, 23);
		//btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		//btnNewButton.setBorder(null);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("返回管理界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppTable window = new AppTable();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setForeground(Color.CYAN);
		button.setBounds(227, 229, 121, 23);
		button.setContentAreaFilled(false);
		frame.getContentPane().add(button);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 15));
		textField.setForeground(Color.GREEN);
		textField.setEditable(false);
		textField.setOpaque(false);

		try {
			textField.setText(dao.getStatId(TempData.priId+""));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds(118, 43, 87, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 15));
		textField_1.setForeground(Color.GREEN);
		//textField_1.setBorder(true);
		textField_1.setColumns(10);
		textField_1.setOpaque(false);
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		textField_1.setBounds(118, 93, 87, 21);
		frame.getContentPane().add(textField_1);
		//textField_2.setOpaque(false);
		//textField_1.setBorder(null);
		
		rdbtnNewRadioButton = new JRadioButton("调入");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setForeground(Color.MAGENTA);
		rdbtnNewRadioButton.setBounds(118, 157, 61, 23);
		rdbtnNewRadioButton.setOpaque(false);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("调出");
		radioButton.setForeground(Color.MAGENTA);
		radioButton.setBounds(182, 157, 61, 23);
		radioButton.setOpaque(false);
		frame.getContentPane().add(radioButton);
		
		
	
		ButtonGroup bgroup = new  ButtonGroup();
		bgroup.add(rdbtnNewRadioButton);
		bgroup.add(radioButton);
		
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(AppTable2.class.getResource("/Application/pic1.jpg")));
		lblNewLabel_2.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
