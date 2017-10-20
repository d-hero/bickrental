package Application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.zrgj.bickrental.dao.JouRecoDao;
import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.UseRecord;
import com.zrgj.bickrental.entity.User;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;

public class BurseFrame {

	JFrame frame;
	JFrame beforeFrame;
	private JPanel panel;
	private JPanel panel_1;
	private JLabel pledge_label;
	private JLabel balance_label;
	private JButton btnNewButton_1;
	private JButton back_re;
	private JButton button;
	private UserDao userDao = new UserDao();
	private JouRecoDao jouRecoDao  = new JouRecoDao();
	


	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BurseFrame window = new BurseFrame();
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
	public BurseFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
	public  void showBalance_And_Pledge(){
	
		Double[] dou = userDao.queryUserBalance_And_Pledge(TempData.user);
		if(dou !=null){
			balance_label.setText(dou[0].toString()+"元");
			pledge_label.setText(dou[1].toString()+"元");
			if(dou[1] ==0){
				btnNewButton_1.setVisible(true);
				back_re.setVisible(false);
			}else{
				btnNewButton_1.setVisible(false);
				back_re.setVisible(true);
			}
		}
	}
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BurseFrame.class.getResource("/image/logo.png")));
		frame.setTitle("我的钱包");
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				showBalance_And_Pledge();	
			}
		});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				userDao.ut.releaseConn();
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());
				
			}
		});
		frame.getContentPane().setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("钱包");
		lblNewLabel.setBounds(10, 10, 101, 39);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 15));
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
				frame.setVisible(false);
				RechargeFrame window = new RechargeFrame();
				window.beforeFrame = frame;
				window.frame.setLocation(frame.getLocation());
				window.frame.setVisible(true);
				window.frame.setVisible(true);
			

			}
		});
		btnNewButton.setBorder(null);
		btnNewButton.setIcon(new ImageIcon(BurseFrame.class.getResource("/image/burseFrame_button1.png")));
	
		btnNewButton.setBounds(235, 145, 99, 43);
		frame.getContentPane().add(btnNewButton);
		
		panel = new JPanel(){
            @Override  
            protected void paintComponent(Graphics g) {  
                 super.paintComponent(g);  
                ImageIcon img = new ImageIcon("src/image/burseFrame_label1.png");    
                img.paintIcon(this, g, 0, 0);  
            }  
       };  
		panel.setBounds(85, 85, 258, 128);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		balance_label = new JLabel("");
		balance_label.setBounds(4, 75, 130, 27);
		panel.add(balance_label);
		balance_label.setFont(new Font("宋体", Font.BOLD, 23));
		
		panel_1 = new JPanel(){
            @Override  
            protected void paintComponent(Graphics g) {  
                super.paintComponent(g);  
                ImageIcon img = new ImageIcon("src/image/burstFrame_label2.png");  
               // ImageIcon img = new ImageIcon("src/image/burseFrame_label1.png");    
                img.paintIcon(this, g, 0, 0);  
            }  
       };  
		panel_1.setBounds(85, 226, 258, 128);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		pledge_label = new JLabel("");
	
		pledge_label.setBounds(82, 39, 162, 40);
		pledge_label.setBorder(null);
	
		
		panel_1.add(pledge_label);
		pledge_label.setFont(new Font("宋体", Font.BOLD, 23));
		
		btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				
			}
			
			
		});
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setIcon(new ImageIcon(BurseFrame.class.getResource("/image/pay_deposit.png")));
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				PayDeposit window = new PayDeposit();
				window.beforeFrame = frame;
				window.beforeFrame2 = null;
				window.frame.setLocation(frame.getLocation());			
				window.frame.setVisible(true);	
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.BOLD, 23));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBounds(82, 80, 92, 40);
		panel_1.add(btnNewButton_1);
		
		back_re = new JButton("");
		back_re.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane jOptionPane = new  JOptionPane();
			
				int confirmDialog = jOptionPane.showConfirmDialog(frame, "是否退押金", "提示", 0, 0);
			
				
				
				UseRecord queryRecord = jouRecoDao.queryNotReturnRecord(TempData.user.getUserId());
				
				if(confirmDialog == 0 && queryRecord == null){
					
					User user = TempData.user;
					user.setPledge(0.0);
					boolean updateUserPledge = userDao.updateUserPledge(user);
					
					
					frame.setVisible(false);
					PledgeBack window = new PledgeBack();
					window.beforeFrame = frame;
					window.frame.setLocation(frame.getLocation());
					window.frame.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(frame, "有正在借的车未还,请先归还车再退押金!");
				}
			
	
			}
		});
		back_re.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {	
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});

		
		back_re.setFocusPainted(false);
		back_re.setBorder(null);
		back_re.setBounds(82, 80, 92, 40);
		panel_1.add(back_re);
		back_re.setIcon(new ImageIcon(BurseFrame.class.getResource("/image/back_deposit .png")));
		
		button = new JButton("\u8FD4\u56DE");
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
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setFocusPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(295, 20, 113, 27);
		frame.getContentPane().add(button);
		frame.setBounds(100, 100, 440, 454);


	}
}
