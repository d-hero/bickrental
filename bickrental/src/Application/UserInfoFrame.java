package Application;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.DateChooser;
import com.zrgj.utils.StringUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class UserInfoFrame {

	JFrame frame;
	JFrame beforeFrame;
	private JTextField userNametextField;
	private JLabel userPhoneLabel;
	private DateChooser dc;
	private JRadioButton sexRadioButton1;
	private JRadioButton sexRadioButton2;
	private  UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoFrame window = new UserInfoFrame();
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
	public UserInfoFrame() {
		initialize();
	}

	
	public void showInfo(){
		User user = TempData.user;
		UserDao userDao = new UserDao();
		User queryUser = userDao.queryUser(user);
		
		if(queryUser.getName() != null){
			userNametextField.setText(queryUser.getName());
		}else{
			userNametextField.setText("");
		}
		
		if(queryUser.getSex() != null){
			
			if(queryUser.getSex().equals("男")){
				sexRadioButton1.setSelected(true);
			}else{
				sexRadioButton2.setSelected(true);
			}

		}
		
		String str = user.getAccount();
		
		char[] charArray = str.toCharArray();
		charArray[3]='*';
		charArray[4]='*';
		charArray[5]='*';
		charArray[6]='*';
		str = String.valueOf(charArray);
		
		userPhoneLabel.setText(str);

	    
		if(queryUser.getBirthday() !=null){
			dc.setText(queryUser.getBirthday());
		}else{
			dc.setText("");
		}
		
		
		
	}
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.setTitle("个人信息");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(UserInfoFrame.class.getResource("/image/logo.png")));
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				showInfo();
			}
		});
		frame.getContentPane().setBackground(Color.WHITE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {	
				userDao.ut.releaseConn();
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());	
			}
		});
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		dc = new DateChooser(frame);
		dc.getDateField().setFont(new Font("宋体", Font.PLAIN, 15));
		dc.setBounds(140, 269, 155,30);
		frame.getContentPane().add(dc);
		
		JLabel lblNewLabel = new JLabel("昵称");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel.setBounds(42, 80, 77, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("性别");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(42, 206, 77, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("出生日期");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(42, 269, 77, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u7ED1\u5B9A\u624B\u673A\u53F7");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(42, 143, 77, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton button = new JButton("");
		button.setIcon(new ImageIcon(UserInfoFrame.class.getResource("/image/saveInfo.png")));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				String name = userNametextField.getText();
				String sex = null;
				if(sexRadioButton1.isSelected()){
					sex = "男";
				}else{
					sex = "女";
				}
								
				String birthday = dc.getText();
			
				
			
					
				
					User user = new User();
					user.setName(name);
					user.setSex(sex);
					user.setBirthday(birthday);
							
					user.setUserId(TempData.user.getUserId());
					boolean updateUser = userDao.updateUser(user);
					if(updateUser){
						JOptionPane.showMessageDialog(frame, "保存成功!");
					}else{
						JOptionPane.showMessageDialog(frame, "网络忙，请稍后再试!");
					}
						
				
				
			}
		});
		button.setBounds(164, 336, 110, 50);
		frame.getContentPane().add(button);
		
		JLabel label = new JLabel("编辑个人信息");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(27, 26, 135, 36);
		frame.getContentPane().add(label);
		
		JButton button_1 = new JButton("\u8FD4\u56DE");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 15));
		button_1.setFocusPainted(false);
		button_1.setBackground(Color.WHITE);
		button_1.setBounds(295, 13, 113, 27);
		frame.getContentPane().add(button_1);
		
		userNametextField = new JTextField();
		userNametextField.setFont(new Font("宋体", Font.PLAIN, 15));
		userNametextField.setBounds(140, 80, 119, 30);
		frame.getContentPane().add(userNametextField);
		userNametextField.setColumns(10);
		
		userPhoneLabel = new JLabel("");
		userPhoneLabel.setBounds(140, 143, 117, 30);
		frame.getContentPane().add(userPhoneLabel);
		
		sexRadioButton1 = new JRadioButton("\u7537");
		sexRadioButton1.setFont(new Font("宋体", Font.PLAIN, 15));
		sexRadioButton1.setBackground(Color.WHITE);
		sexRadioButton1.setBounds(140, 206, 51, 27);
		frame.getContentPane().add(sexRadioButton1);
		
		sexRadioButton2 = new JRadioButton("\u5973");
		sexRadioButton2.setFont(new Font("宋体", Font.PLAIN, 15));
		sexRadioButton2.setBackground(Color.WHITE);
		sexRadioButton2.setBounds(230, 206, 51, 27);
		frame.getContentPane().add(sexRadioButton2);
		
		ButtonGroup  bt = new ButtonGroup();
		bt.add(sexRadioButton1);
		bt.add(sexRadioButton2);
	}
}
