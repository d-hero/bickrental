package Application;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JRadioButton;

import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class PayDeposit {

	JFrame frame;
	JFrame beforeFrame;
	JFrame beforeFrame2;
	private UserDao userDao = new UserDao();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayDeposit window = new PayDeposit();
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
	public PayDeposit() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("押金充值");
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PayDeposit.class.getResource("/image/logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {	
				userDao.ut.releaseConn();
			if(beforeFrame !=null){
				beforeFrame.setLocation(frame.getLocation());
				beforeFrame.setVisible(true);
			}else{
				beforeFrame2.setLocation(frame.getLocation());
				beforeFrame2.setVisible(true);
			}
			
			}
		});
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(PayDeposit.class.getResource("/image/zhifubao_pay.png")));
		label.setBounds(30, 259, 149, 33);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(PayDeposit.class.getResource("/image/wechat_pay.png")));
		label_1.setBounds(30, 213, 149, 33);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(PayDeposit.class.getResource("/image/select_pay.png")));
		label_2.setBounds(30, 148, 166, 30);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(PayDeposit.class.getResource("/image/posi.png")));
		label_3.setFont(new Font("宋体", Font.BOLD, 15));
		label_3.setBounds(30, 32, 362, 111);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("");
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
		button.setBorder(null);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				User user = TempData.user;
				user.setPledge(150.00);
				boolean updateUserPledge = userDao.updateUserPledge(user);
				
				if(updateUserPledge){
					JOptionPane.showMessageDialog(frame, "交押金成功!");
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(frame, "网络忙，请稍后再试!");
				}
				
			}
		});
		button.setIcon(new ImageIcon(PayDeposit.class.getResource("/image/pay_now.png")));
		button.setBounds(65, 331, 296, 51);
		frame.getContentPane().add(button);
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setSelected(true);
		radioButton.setBackground(Color.WHITE);
		radioButton.setBounds(220, 213, 25, 33);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setBounds(220, 259, 25, 33);
		frame.getContentPane().add(radioButton_1);
		
		
		ButtonGroup  buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton_1);
		buttonGroup.add(radioButton);
		
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
		button_1.setBounds(295, 3, 113, 27);
		frame.getContentPane().add(button_1);
	}
}
