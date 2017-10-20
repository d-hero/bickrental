package Application;



import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import com.zrgj.bickrental.entity.Admin;



import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JAdminFrame {

	JFrame frame;
	
	private Admin admin = TempData.admin;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JAdminFrame window = new JAdminFrame();
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
	public JAdminFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("管理车辆调度");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandTable window = new HandTable();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(85, 78, 166, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("管理车辆负责人");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddPriMessage window = new AddPriMessage();
				window.frame.setVisible(true);
				window.frame.setVisible(true);
			}
		});
		
		btnNewButton_1.setBounds(85, 147, 166, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		final JLabel label01 = new JLabel("你好"+admin.getName());
		
		
		label01.setBounds(20, 28, 148, 15);
		frame.getContentPane().add(label01);

		 final JLabel Label02 = new JLabel("当前时间");
		frame.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				Label02.setText("当前登录时间"+new Date().toLocaleString());
		}
		});	
		Label02.setBounds(199, 237, 225, 15);
		frame.getContentPane().add(Label02);
        

	}
}
