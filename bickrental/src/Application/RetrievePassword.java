package Application;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.StringUtils;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;


public class RetrievePassword {

	
	JFrame frame;
	JFrame beforeFrame;
	private JTextField userPhoneTextfield;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JLabel pwd1_Label1;
	private JLabel pwd1_Label2;
	private JLabel pwd2_Label1;
	private JLabel pwd2_Label2;
	private JLabel username_Label;
	private JLabel username_Label1;
	private JLabel username_Label2;
	
	private boolean userNameTextfield_isRight = false;
	private boolean passwordField_isRight = false;
	
	private String account;
	private JLabel label_3;
	private JTextField CodetextField;
	private JLabel showTimeDownLabel;
	private JButton getCodeButton;
	private boolean isRegiste = false;
	private UserDao userDao = new UserDao();
	
	
	//内部类 计时器
	
	class TimeDown extends Thread{
	     
	    public  int start = 300;
		public void run(){
			showTimeDownLabel.setVisible(true);
			getCodeButton.setVisible(false);
			
			while(true){
				try {
					if(start == 0){
						showTimeDownLabel.setText("");
						showTimeDownLabel.setVisible(false);
						getCodeButton.setVisible(true);
						break;
					}
					showTimeDownLabel.setText(String.valueOf(start)+"秒后获取");
					start = start - 1;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
		}

	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetrievePassword window = new RetrievePassword();
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
	public RetrievePassword() {
		initialize();
	}

	
	// 检查输入手机号
	public void checkAccount() {
		account = userPhoneTextfield.getText();
		String regex = "^1[3|4|5|7|8][0-9]{9}$";

		if (!account.matches(regex)) {
			username_Label.setIcon(new ImageIcon("src/image/warn.png"));
			if (!StringUtils.isEmpty(account)) {
				userNameTextfield_isRight = false;
				username_Label1.setIcon(new ImageIcon("src/image/warn.png"));
				username_Label2.setText("请输入正确的手机号");
			}
		} else {

			User user = new User(account);
			UserDao userDao = new UserDao();
			User queryUser = userDao.queryUserId(user);
		

			if (queryUser != null) {
				userNameTextfield_isRight = true;
				isRegiste = true;
				username_Label2.setText("");
				username_Label1.setIcon(new ImageIcon("src/image/empty.png"));
				username_Label.setIcon(new ImageIcon("src/image/right.png"));

			} else {
				isRegiste = false;
				userNameTextfield_isRight = false;
				username_Label2.setText("此手机号还未注册");
				username_Label1.setIcon(new ImageIcon("src/image/warn.png"));
				username_Label.setIcon(new ImageIcon("src/image/warn.png"));

			}
		}

	}
	
	//检查密码
	public void checkPwd(){
		String pwd = String.valueOf(passwordField.getPassword());
		String pwd1 = String.valueOf(passwordField_1.getPassword());
		
		if(!pwd.equals(pwd1)){
			passwordField_isRight = false;
			pwd2_Label1.setIcon(new ImageIcon("src/image/warn.png"));
			pwd2_Label2.setText("输入密码不一致");
		}else if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(pwd1)){
			passwordField_isRight = false;
		}else {
			passwordField_isRight = true;
		}
	}
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RetrievePassword.class.getResource("/image/logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				userDao.ut.releaseConn();
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());	
			}
		});
		frame.setResizable(false);
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("\u627E\u56DE\u5BC6\u7801");
		frame.setBounds(100, 100, 440, 454);
	
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		

	
		
		JLabel lblNewLabel = new JLabel("\u624B\u673A\u53F7\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(54, 60, 60, 30);
		frame.getContentPane().add(lblNewLabel);
		
	
		
		JLabel label = new JLabel("\u65B0\u5BC6\u7801");
		label.setFont(new Font("宋体", Font.PLAIN, 12));
		label.setBounds(54, 170, 60, 30);
		frame.getContentPane().add(label);
		
		
		JLabel label_1 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(54, 245, 60, 30);
		frame.getContentPane().add(label_1);
		
	
		
		userPhoneTextfield = new JTextField();
		userPhoneTextfield.setFont(new Font("宋体", Font.PLAIN, 15));
		userPhoneTextfield.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {		
				checkAccount();
			}
			@Override
			public void focusGained(FocusEvent e) {	
				
				
				
				
		     	username_Label2.setText("");
				username_Label1.setIcon(new ImageIcon("src/image/empty.png"));
			}
		});
		
		userPhoneTextfield.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				userPhoneTextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				userPhoneTextfield.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 173, 179)));
			}
		});
		userPhoneTextfield.setBounds(120, 60, 252, 30);
		
		frame.getContentPane().add(userPhoneTextfield);
		
		
		
		userPhoneTextfield.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String pwd = String.valueOf(passwordField.getPassword());
				
				if(StringUtils.isEmpty(pwd)){
					passwordField_1.setText("");
					pwd2_Label1.setIcon(new ImageIcon("src/image/empty.png"));
					pwd2_Label2.setText("");
				}	
			}		
		});
		
		
		
		
		
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				passwordField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 173, 179)));
			}
		});
		passwordField.setBounds(120, 170, 252, 30);
		frame.getContentPane().add(passwordField);
	
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 15));
		passwordField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

				checkPwd();		
			}
		});
	
		
		passwordField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				passwordField_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				passwordField_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 173, 179)));
			}
		});
		passwordField_1.setBounds(120, 245, 252, 30);
		frame.getContentPane().add(passwordField_1);
		
		
		

		Document document = passwordField.getDocument();  
        document.addDocumentListener(new  DocumentListener(){
        	
        	
        	public void check(){
        
				String pwd = String.valueOf(passwordField.getPassword());
		
				if(StringUtils.isEmpty(pwd)){
					passwordField_1.setText("");
					pwd2_Label1.setIcon(new ImageIcon("src/image/empty.png"));
					pwd2_Label2.setText("");
				}
				
				
				if(pwd.length() < 6 || pwd.length() > 16){
					pwd1_Label2.setIcon(new ImageIcon("src/image/warn.png"));
				}else{
					pwd1_Label2.setIcon(new ImageIcon("src/image/right.png"));
				}
				
				if(pwd.contains(" ")){
					pwd1_Label1.setIcon(new ImageIcon("src/image/warn.png"));
				
				}else{
					pwd1_Label1.setIcon(new ImageIcon("src/image/right.png"));
				}
	
        	}
        	

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();		
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();	
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();
			} 	
        	
        }); 
		
			
        Document document1 = passwordField_1.getDocument();  
        document1.addDocumentListener(new  DocumentListener(){
        	
        	public void check(){
        		String pwd = String.valueOf(passwordField.getPassword());
				String pwd1 = String.valueOf(passwordField_1.getPassword());
				
				if(StringUtils.isEmpty(pwd1)){
					pwd2_Label1.setIcon(new ImageIcon("src/image/empty.png"));
					pwd2_Label2.setText("");
				}
				else if(!pwd.startsWith(pwd1)){
					pwd2_Label1.setIcon(new ImageIcon("src/image/warn.png"));
					pwd2_Label2.setText("输入密码不一致");
					
				}else{
					pwd2_Label1.setIcon(new ImageIcon("src/image/right.png"));
					pwd2_Label2.setText("");
				}
        	}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
				check();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
				check();
			} 	
        	
        }); 
		
		
		
		JSeparator separator = new JSeparator();
		separator.setBounds(35, 44, 345, 2);
		frame.getContentPane().add(separator);
		
		
		JLabel lblNewLabel_1 = new JLabel("\u627E\u56DE\u5BC6\u7801");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(47, 24, 72, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
		btnNewButton.setIcon(new ImageIcon(RetrievePassword.class.getResource("/image/confirm.png")));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userNameTextfield_isRight){
					JOptionPane.showMessageDialog(frame, "请输入正确的手机号!!!");
				}
	
				else if (!CodetextField.getText().equals("666666")) {
					JOptionPane.showMessageDialog(frame, "验证码错误!!!");
				} else {

					if ( passwordField_isRight) {
						String account = userPhoneTextfield.getText();
						String pwd = String.valueOf(passwordField.getPassword());
					
					
						
						User user = new User();
						user.setAccount(account);
						user.setPassword(pwd);
					
					
						boolean res = userDao.updateUserPassword(user);
						if (res) {
							JOptionPane.showMessageDialog(frame, "设置成功!!!");
							frame.dispose();

						} else {
							JOptionPane.showMessageDialog(frame, "网络忙，请稍后再试试!!!");
						}

					} else {
						JOptionPane.showMessageDialog(frame, "请输入密码!!!");
					}

				}

			}
		});
		btnNewButton.setBounds(160, 320, 110, 50);
		frame.getContentPane().add(btnNewButton);
		
		pwd1_Label1 = new JLabel("");
		pwd1_Label1.setFont(new Font("宋体", Font.PLAIN, 15));
		pwd1_Label1.setIcon(new ImageIcon(Registe.class.getResource("/image/initi.png")));
		pwd1_Label1.setBounds(120, 210, 30, 30);
		frame.getContentPane().add(pwd1_Label1);
		
		pwd1_Label2 = new JLabel("");
		pwd1_Label2.setFont(new Font("宋体", Font.PLAIN, 15));
		pwd1_Label2.setIcon(new ImageIcon(Registe.class.getResource("/image/initi.png")));
		pwd1_Label2.setBounds(234, 210, 30, 30);
		frame.getContentPane().add(pwd1_Label2);
		
		JLabel lblNewLabel_3 = new JLabel("不能包含空格");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(150, 210, 80, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("长度为6-16个字符");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(264, 210, 108, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		pwd2_Label1 = new JLabel("");
		pwd2_Label1.setBounds(120, 277, 30, 30);
		frame.getContentPane().add(pwd2_Label1);
		
		pwd2_Label2 = new JLabel("");
		pwd2_Label2.setFont(new Font("宋体", Font.PLAIN, 12));
		pwd2_Label2.setBounds(150, 277, 154, 30);
		frame.getContentPane().add(pwd2_Label2);
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(377, 215, 30, 30);
		frame.getContentPane().add(label_6);
		
		username_Label = new JLabel("");
		username_Label.setIcon(new ImageIcon(Registe.class.getResource("/image/initi.png")));
		username_Label.setBounds(377, 60, 30, 30);
		frame.getContentPane().add(username_Label);
		
		username_Label1 = new JLabel("");
		username_Label1.setBounds(120, 95, 30, 30);
		frame.getContentPane().add(username_Label1);
		
		username_Label2 = new JLabel("");
		username_Label2.setFont(new Font("宋体", Font.PLAIN, 12));
		username_Label2.setBounds(150, 95, 222, 30);
		frame.getContentPane().add(username_Label2);
		
		JButton button = new JButton("\u8FD4\u56DE");
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
				frame.dispose();
			}
		});
		button.setFocusPainted(false);
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setBackground(Color.WHITE);
		button.setBounds(309, 13, 113, 27);
		frame.getContentPane().add(button);
		
		label_3 = new JLabel("\u624B\u673A\u9A8C\u8BC1\u7801");
		label_3.setFont(new Font("宋体", Font.PLAIN, 12));
		label_3.setBounds(54, 125, 60, 30);
		frame.getContentPane().add(label_3);
		
		CodetextField = new JTextField();
		CodetextField.setFont(new Font("宋体", Font.PLAIN, 15));
		CodetextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				CodetextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				CodetextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 173, 179)));
			}
		});
		CodetextField.setColumns(10);
		CodetextField.setBounds(120, 128, 121, 30);
		frame.getContentPane().add(CodetextField);
		
		getCodeButton = new JButton("");
		getCodeButton.setFont(new Font("宋体", Font.PLAIN, 15));
		getCodeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		getCodeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String str = userPhoneTextfield.getText();
				String regex = "^1[3|4|5|7|8][0-9]{9}$";
				if(StringUtils.isEmpty(str)){
					JOptionPane.showMessageDialog(frame, "输入不能为空");
				}else if(!str.matches(regex)){
					JOptionPane.showMessageDialog(frame, "请输入正确的手机号");
				}else if(!isRegiste){
					JOptionPane.showMessageDialog(frame, "此手机号还没注册，不能找回");
				}else{
					new TimeDown().start();
				}
	
			}
		});
		getCodeButton.setIcon(new ImageIcon(Registe.class.getResource("/image/getCode.png")));
		getCodeButton.setBounds(258, 128, 114, 30);
		frame.getContentPane().add(getCodeButton);
		
		showTimeDownLabel = new JLabel("");
		showTimeDownLabel.setVisible(false);
		showTimeDownLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		showTimeDownLabel.setBounds(258, 128, 114, 30);
		frame.getContentPane().add(showTimeDownLabel);

	}
}
