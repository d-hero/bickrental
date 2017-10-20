package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.zrgj.bickrental.dao.AdminDao;
import com.zrgj.bickrental.dao.PersonDao;
import com.zrgj.bickrental.dao.PrincipalDao;
import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.Admin;
import com.zrgj.bickrental.entity.Person;
import com.zrgj.bickrental.entity.Principal;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.StringUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BackStageLogin {

 JFrame frame;
	private JTextField accountField;
	private JPasswordField passwordField;
	private JLabel r_Label1;
	private JLabel r_Label2;
	private JComboBox comboBox;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BackStageLogin window = new BackStageLogin();
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
	public BackStageLogin() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/image/logo.png")));
		frame.setTitle("登录");
		frame.setBounds(100, 100, 442, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 158, 436, 236);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("帐号");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel.setBounds(44, 15, 85, 30);
		panel.add(lblNewLabel);
		
		JButton button = new JButton("登录");
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
		button.setFocusPainted(false);
		button.setBackground(Color.WHITE);
		button.setFont(new Font("宋体", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//获取帐号 密码
				String account = accountField.getText()+"";
				String password = String.valueOf(passwordField.getPassword()); 
			
				
			
				
				if(StringUtils.isEmpty(account)){
				    	  r_Label1.setText("帐号不能为空!");
				    	  r_Label1.setForeground(Color.red); 
				}
				
				
				if(StringUtils.isEmpty(password)){
					    r_Label2.setText("密码不能为空!");
					    r_Label2.setForeground(Color.red);    
				}else {
					String identity = comboBox.getSelectedItem().toString();	
					
					 if(identity.equals("站点负责人")){
							
							Principal principal = new Principal(account,password);
							PrincipalDao principalDao = new PrincipalDao();
							String queryPrincipalId = principalDao.queryPrincipalId(principal);
							if(queryPrincipalId != null){
								
								TempData.priId = queryPrincipalId;
								AppTable window = new AppTable();
								window.frame.setVisible(true);
								frame.setVisible(false);
								
							}else{
								JOptionPane.showMessageDialog(frame, "帐号或密码错误!");
							}
							
							
						}else if(identity.equals("管理员")){
							
							Admin admin = new Admin(account,password);
							AdminDao adminDao = new AdminDao();
							Admin queryAdmin= adminDao.queryAdmin(admin);
							System.out.println(queryAdmin);
							if(queryAdmin != null){
								
								TempData.admin = queryAdmin;
								MainManage window = new MainManage();
								window.frame.setVisible(true);
								frame.setVisible(false);
								
							}else{
								JOptionPane.showMessageDialog(frame, "帐号或密码错误!");
							}
							
						}
	
				}
					
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("密码");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(44, 82, 54, 30);
		panel.add(lblNewLabel_1);
		button.setBounds(147, 183, 153, 40);
		panel.add(button);
		
		accountField = new JTextField();
		accountField.setFont(new Font("宋体", Font.PLAIN, 12));
		accountField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				accountField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 255, 51)));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				accountField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(173, 173, 179)));
			}
		});
		accountField.setBounds(143, 15, 157, 30);
		panel.add(accountField);
		accountField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
		passwordField.setFont(new Font("宋体", Font.PLAIN, 12));
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
		passwordField.setBounds(143, 82, 156, 30);
		panel.add(passwordField);
		
		
		
		Document document1 = accountField.getDocument();  
        document1.addDocumentListener(new  DocumentListener(){
            public void check(){
            	String str = accountField.getText();
            	if(!StringUtils.isEmpty(str)){
            		r_Label1.setText("");
            	}else{
            		  r_Label1.setText("帐号不能为空!");
			    	  r_Label1.setForeground(Color.red); 
            	}
            	
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
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();
			} 	
        
        }); 
		
		
    	Document document2 = passwordField.getDocument();  
        document2.addDocumentListener(new  DocumentListener(){
            public void check(){
            	String str = passwordField.getText();
            	if(!StringUtils.isEmpty(str)){
            		r_Label2.setText("");
            	}else{
            		  r_Label2.setText("密码不能为空!");
			    	  r_Label2.setForeground(Color.red); 
            	}
            	
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
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				check();
			} 	
        
        });
		
		r_Label1 = new JLabel("");
		r_Label1.setFont(new Font("宋体", Font.PLAIN, 12));
		r_Label1.setForeground(Color.BLACK);
		r_Label1.setBackground(Color.WHITE);
		r_Label1.setBounds(143, 53, 157, 23);
		panel.add(r_Label1);
		
		r_Label2 = new JLabel("");
		r_Label2.setFont(new Font("宋体", Font.PLAIN, 12));
		r_Label2.setBounds(143, 120, 157, 15);
		panel.add(r_Label2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"站点负责人", "管理员"}));
		comboBox.setBounds(143, 146, 113, 24);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("类型");
		lblNewLabel_3.setBounds(44, 146, 72, 18);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/image/xiamenbike.png")));
		lblNewLabel_2.setBounds(0, 0, 436, 155);
		frame.getContentPane().add(lblNewLabel_2);
		
	}
}
