package Application;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.StringUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class RechargeFrame {

	JFrame frame;
	JFrame beforeFrame;

	private JTextField rechargeMoneyField;
	private JLabel rechargeMoneyLabel;
	private JButton recharge_button;
	private boolean isRightMoney = false;
	private UserDao userDao = new UserDao();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RechargeFrame window = new RechargeFrame();
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
	public RechargeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.setTitle("余额充值");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RechargeFrame.class.getResource("/image/logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {	
				//BurseFrame.window.showBalance_And_Pledge();
				userDao.ut.releaseConn();
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());
			
			}
		});
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton recharge_button = new JButton("");
		recharge_button.setBorder(null);
		recharge_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		recharge_button.setIcon(new ImageIcon(RechargeFrame.class.getResource("/image/recharge_right.png")));
		recharge_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(isRightMoney){
					Double balance = Double.valueOf(rechargeMoneyField.getText()+" ");
					
					
					User user = new User();
					user.setUserId(TempData.user.getUserId());
					user.setBalance(balance);
					boolean updateUser = userDao.updateUserBalance(user);
					if(updateUser){
						JOptionPane.showMessageDialog(frame, "充值成功");
						frame.dispose();
					}else{
						JOptionPane.showMessageDialog(frame, "网络忙，请稍后再试!");
					}
				}else{
					JOptionPane.showMessageDialog(frame, "请输入正确的充值金额!");
				}
							
			}
		});
		recharge_button.setBounds(65, 331, 296, 51);
		frame.getContentPane().add(recharge_button);
		
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(RechargeFrame.class.getResource("/image/recharge_amount.png")));
		label_2.setFont(new Font("宋体", Font.PLAIN, 15));
		label_2.setBounds(14, 75, 132, 34);
		frame.getContentPane().add(label_2);
		
		rechargeMoneyField = new JTextField();
		rechargeMoneyField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
				String str = rechargeMoneyField.getText();
				
				//检查是否是金额
				
				String regex = "^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$";
			
				
				if(StringUtils.isEmpty(str)){
					isRightMoney = false;
					rechargeMoneyLabel.setIcon(new ImageIcon("src/image/recharge_not_empty.png"));
				}else if(!str.matches(regex)){
					isRightMoney = false ;
					rechargeMoneyLabel.setIcon(new ImageIcon("src/image/wrong_format.png"));
				
				}else{
					isRightMoney = true;
					rechargeMoneyLabel.setIcon(new ImageIcon("src/image/recharge_normal.png"));
				}
				
				
				
			}
		});
		rechargeMoneyField.setFont(new Font("宋体", Font.PLAIN, 25));
		rechargeMoneyField.setBounds(160, 75, 129, 34);
		frame.getContentPane().add(rechargeMoneyField);
		rechargeMoneyField.setColumns(10);
		
		
		
		
		
		
		JRadioButton radioButton = new JRadioButton("");
		radioButton.setBackground(Color.WHITE);
		radioButton.setSelected(true);
		radioButton.setBounds(207, 210, 25, 33);
		frame.getContentPane().add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("");
		radioButton_1.setBackground(Color.WHITE);
		radioButton_1.setBounds(207, 256, 25, 34);
		frame.getContentPane().add(radioButton_1);
		
		ButtonGroup  buttonGroup = new ButtonGroup();
		buttonGroup.add(radioButton);
		buttonGroup.add(radioButton_1);
		
		
		JLabel label_3 = new JLabel("元");
		label_3.setFont(new Font("宋体", Font.BOLD, 25));
		label_3.setBounds(303, 75, 58, 34);
		frame.getContentPane().add(label_3);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RechargeFrame.class.getResource("/image/wechat_pay.png")));
		lblNewLabel.setBounds(14, 210, 149, 33);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(RechargeFrame.class.getResource("/image/zhifubao_pay.png")));
		lblNewLabel_1.setBounds(14, 256, 149, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RechargeFrame.class.getResource("/image/select_pay.png")));
		lblNewLabel_2.setBounds(14, 145, 166, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
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
		
		rechargeMoneyLabel = new JLabel("");
		rechargeMoneyLabel.setBounds(162, 112, 246, 24);
		frame.getContentPane().add(rechargeMoneyLabel);
	}
}
