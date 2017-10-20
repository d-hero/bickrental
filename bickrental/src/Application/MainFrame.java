package Application;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.User;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class MainFrame {

	JFrame frame;
	private JButton	 show_button;
	private JButton btnNewButton_1 ;
	private boolean Is_Pay_Deposit;
    private BroUI window = new BroUI();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
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
	public MainFrame() {
		initialize();
	}

	
	
	public void checkDeposit(){
		UserDao userDao = new UserDao();
		User user  = TempData.user;
		Double[] dou = userDao.queryUserBalance_And_Pledge(user);
		
		Double pledge = dou[1];
		if(pledge == 0){
			Is_Pay_Deposit = false;
			show_button.setVisible(true);
			ImageIcon  imageIcon = new ImageIcon("src/image/not_pay_deposit.png");
			show_button.setIcon(imageIcon);
		}else{
			Is_Pay_Deposit = true;
			show_button.setVisible(false);
		}
	}
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("厦门公共单车");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/image/logo.png")));
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				checkDeposit();	
			}
		});
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(244,196,16));
		panel.setBounds(0, 0, 424, 416);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
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
				UserInfoFrame window = new UserInfoFrame();
				window.beforeFrame = frame;
				window.frame.setLocation(frame.getLocation());
				window.frame.setVisible(true);
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(63, 113, 124, 95);
		panel.add(btnNewButton);
		
	    btnNewButton_1 = new JButton("\u7528\u8F66");
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
	    btnNewButton_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(! Is_Pay_Deposit ){
	    			JOptionPane.showMessageDialog(btnNewButton_1, "请先交押金，再用车！");
	    		}else{
	    			frame.setVisible(false);
	    			
	    			window.beforeFrame = frame;
	    			window.frame.setLocation(frame.location());
					window.frame.setVisible(true);
	    		}
	    	}
	    });
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(237, 113, 124, 95);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("\u884C\u7A0B");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				JouRecord window = new JouRecord();
				window.beforeFrame = frame ;
				window.frame1.setLocation(frame.getLocation());
				window.frame1.setVisible(true);
					
			}
		});
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnNewButton_2.setFocusPainted(false);
		btnNewButton_2.setBackground(Color.YELLOW);
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(63, 266, 124, 95);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\u94B1\u5305");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				BurseFrame window = new BurseFrame();
				window.beforeFrame = frame;
				window.frame.setLocation(frame.getLocation());
				window.frame.setVisible(true);
			}
		});
		btnNewButton_3.setFocusPainted(false);
		btnNewButton_3.setBackground(Color.YELLOW);
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton_3.setBounds(237, 266, 124, 95);
		panel.add(btnNewButton_3);
		
		show_button = new JButton("");
		show_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		show_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				frame.setVisible(false);
				PayDeposit window = new PayDeposit();
				window.beforeFrame2 = frame;
				window.beforeFrame = null;
				window.frame.setLocation(frame.getLocation());			
				window.frame.setVisible(true);			
			}
		});
		show_button.setBounds(63, 36, 360, 55);
		panel.add(show_button);

	}
}
