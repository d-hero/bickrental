package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

import com.zrgj.bickrental.dao.StationDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class AppTable {

	JFrame frame;
	private JTextField textField;
	StationDao dao=new StationDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppTable window = new AppTable();
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
	public AppTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AppTable.class.getResource("/image/logo.png")));
		frame.setTitle("站点管理");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("管理我的站点");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 24));
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(124, 10, 151, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("本站点目前拥有车辆：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(20, 60, 211, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setForeground(Color.RED);
		textField.setEditable(false);
		textField.setOpaque(false);
		try {
			int bnum=dao.getNum(TempData.priId+"");
			textField.setText(bnum+"");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setBounds(215, 64, 81, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("申请车辆调度");
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppTable2 window = new AppTable2();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setBounds(81, 229, 116, 23);
		btnNewButton.setContentAreaFilled(false);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("退出");
		button.setForeground(Color.CYAN);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int n=JOptionPane.showConfirmDialog(null, "是否退出", "共享单车管理系统", JOptionPane.YES_NO_OPTION);
				if(n==JOptionPane.YES_OPTION)
				{	
					System.exit(0);
					
				}
		}
		});
		button.setBounds(238, 229, 116, 23);
		button.setContentAreaFilled(false);
		frame.getContentPane().add(button);
		
		JLabel lblNewLabel_2 = new JLabel("辆");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(304, 61, 33, 22);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblNewLabel_3.setIcon(new ImageIcon(AppTable.class.getResource("/Application/pic1.jpg")));
		lblNewLabel_3.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
