package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainManage {

	 JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainManage window = new MainManage();
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
	public MainManage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AppTable.class.getResource("/image/logo.png")));
		frame.setTitle("管理员界面");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("共享单车管理界面");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(75, 10, 257, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("管理车辆调度");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandTable window = new HandTable();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBounds(130, 52, 149, 23);
		btnNewButton.setContentAreaFilled(false);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("管理站点负责人");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManPriMessage window = new ManPriMessage();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setForeground(Color.CYAN);
		button.setBounds(130, 104, 149, 23);
		button.setContentAreaFilled(false);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("切换账号");
		button_1.setForeground(Color.CYAN);
		button_1.setBounds(130, 157, 149, 23);
		button_1.setContentAreaFilled(false);
		frame.getContentPane().add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackStageLogin window = new BackStageLogin();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		JButton button_2 = new JButton("退出");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setForeground(Color.CYAN);
		button_2.setBounds(130, 213, 149, 23);
		button_2.setContentAreaFilled(false);
		frame.getContentPane().add(button_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainManage.class.getResource("/Application/pic1.jpg")));
		lblNewLabel_1.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
