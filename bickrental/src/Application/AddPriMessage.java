package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.zrgj.bickrental.dao.PrincipalDao;
import com.zrgj.utils.StringUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JRadioButton;

public class AddPriMessage {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	PrincipalDao dao=new PrincipalDao();
	JRadioButton rdbtnNewRadioButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPriMessage window = new AddPriMessage();
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
	public AddPriMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AppTable.class.getResource("/image/logo.png")));
		frame.setTitle("添加负责人信息");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("站点负责人信息添加");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setBounds(114, 10, 203, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("负责人ID：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(10, 43, 75, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("账号：");
		label.setForeground(Color.RED);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(10, 77, 75, 24);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("性别：");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(10, 125, 75, 24);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("地址：");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(10, 170, 75, 24);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("姓名：");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(206, 43, 75, 24);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("密码：");
		label_4.setForeground(Color.RED);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(206, 77, 75, 24);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("出生日期：");
		label_5.setForeground(Color.RED);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(206, 125, 75, 24);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("联系方式：");
		label_6.setForeground(Color.RED);
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(206, 170, 75, 24);
		frame.getContentPane().add(label_6);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.BOLD, 15));
		textField.setForeground(Color.YELLOW);
		textField.setEditable(false);
		textField.setOpaque(false);
		textField.setBounds(76, 45, 97, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		try {
			String pid=dao.getPid();
			pid=StringUtils.dealId(pid);
			textField.setText(pid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.BOLD, 15));
		textField_1.setForeground(Color.YELLOW);
		textField_1.setColumns(10);
		textField_1.setBounds(76, 77, 97, 21);
		textField_1.setOpaque(false);
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.BOLD, 15));
		textField_3.setForeground(Color.YELLOW);
		textField_3.setColumns(10);
		textField_3.setBounds(76, 172, 97, 21);
		textField_3.setOpaque(false);
		textField_3.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.BOLD, 15));
		textField_4.setForeground(Color.YELLOW);
		textField_4.setColumns(10);
		textField_4.setOpaque(false);
		textField_4.setHorizontalAlignment(JTextField.CENTER);
		textField_4.setBounds(275, 44, 97, 21);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("宋体", Font.BOLD, 15));
		textField_5.setForeground(Color.YELLOW);
		textField_5.setColumns(10);
		textField_5.setOpaque(false);
		textField_5.setHorizontalAlignment(JTextField.CENTER);
		textField_5.setBounds(275, 79, 97, 21);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("宋体", Font.BOLD, 15));
		textField_6.setForeground(Color.YELLOW);
		textField_6.setColumns(10);
		textField_6.setBounds(275, 127, 97, 21);
		textField_6.setOpaque(false);
		textField_6.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("宋体", Font.BOLD, 15));
		textField_7.setForeground(Color.YELLOW);
		textField_7.setColumns(10);
		textField_7.setOpaque(false);
		textField_7.setHorizontalAlignment(JTextField.CENTER);
		textField_7.setBounds(275, 172, 97, 21);
		frame.getContentPane().add(textField_7);
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()){
				try {
					dao.insertTable(textField.getText(), textField_4.getText(),
							textField_1.getText(), textField_5.getText(), "男",
							textField_6.getText(), textField_3.getText(), textField_7.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}else{
					try {
						dao.insertTable(textField.getText(), textField_4.getText(),
								textField_1.getText(), textField_5.getText(), "女",
								textField_6.getText(), textField_3.getText(), textField_7.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "添加成功！");
			}
		});
		
		rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setForeground(Color.CYAN);
		rdbtnNewRadioButton.setBounds(79, 126, 55, 23);
		rdbtnNewRadioButton.setOpaque(false);
		frame.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("女");
		radioButton.setOpaque(false);
		radioButton.setForeground(Color.CYAN);
		radioButton.setBounds(136, 126, 56, 23);
		frame.getContentPane().add(radioButton);
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBounds(76, 217, 116, 23);
		btnNewButton.setContentAreaFilled(false);
		frame.getContentPane().add(btnNewButton);
		
		ButtonGroup bgroup = new  ButtonGroup();
		bgroup.add(rdbtnNewRadioButton);
		bgroup.add(radioButton);
		
		JButton button = new JButton("返回管理界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManPriMessage window = new ManPriMessage();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setForeground(Color.CYAN);
		button.setBounds(275, 217, 116, 23);
		button.setContentAreaFilled(false);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("l");
		lblNewLabel_2.setIcon(new ImageIcon(AddPriMessage.class.getResource("/Application/pic1.jpg")));
		lblNewLabel_2.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
