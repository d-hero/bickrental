package Application;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import com.zrgj.bickrental.dao.PrincipalDao;
import com.zrgj.bickrental.entity.ApplyClass;
import com.zrgj.bickrental.entity.Principal;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManPriMessage {

	 JFrame frame;
	private JTable table;
	PrincipalDao dao=new PrincipalDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManPriMessage window = new ManPriMessage();
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
	public ManPriMessage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AppTable.class.getResource("/image/logo.png")));
		frame.setTitle("管理负责人信息");
		frame.setBounds(100, 100, 583, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 547, 221);
		frame.getContentPane().add(scrollPane);
		
		
		List<Principal> pri;
		Object[][] data = null;
		try {
			pri =  dao.queryPrincipal();
			data = new Object[pri.size()][8];
			for(int i = 0;i<pri.size();i++){
				Principal a = pri.get(i);
				data[i][0] = a.getpId();
				data[i][1] = a.getName();
				data[i][2] = a.getAccount();
				data[i][3] = a.getPassword();
				data[i][4] = a.getSex();
				data[i][5] = a.getBirthday();
				data[i][6] = a.getAddress();
				data[i][7] = a.getTelephone();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			data,
			new String[] {
				"负责人编号", "姓名", "账号", "密码", "性别", "生日", "地址", "联系方式"
			}
		));
		table.getColumnModel().getColumn(5).setPreferredWidth(86);
		table.getColumnModel().getColumn(7).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("添加负责人信息");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPriMessage window = new AddPriMessage();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setBounds(55, 258, 128, 23);
		btnNewButton.setContentAreaFilled(false);
		frame.getContentPane().add(btnNewButton);
		
		JButton button = new JButton("更新信息");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i=0;i<table.getRowCount();i++){
				Principal p=new Principal();
				String value = (String) table.getValueAt(i, 0);
				p.setpId(value);
				value = (String) table.getValueAt(i, 1);
				p.setName(value);
				value = (String) table.getValueAt(i, 2);
				p.setAccount(value);
				value = (String) table.getValueAt(i, 3);
				p.setPassword(value);
				value = (String) table.getValueAt(i, 4);
				p.setSex(value);
				value = (String) table.getValueAt(i, 5);
				p.setBirthday(value);
				value = (String) table.getValueAt(i, 6);
				p.setAddress(value);
				value = (String) table.getValueAt(i, 7);
				p.setTelephone(value);
				try {
					dao.updateTable(p);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				JOptionPane.showMessageDialog(null, "信息修改成功！");
			}
		});
		button.setForeground(Color.CYAN);
		button.setBounds(221, 258, 117, 23);
		button.setContentAreaFilled(false);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("返回管理界面");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainManage window = new MainManage();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button_1.setForeground(Color.CYAN);
		button_1.setBounds(376, 258, 117, 23);
		button_1.setContentAreaFilled(false);
		frame.getContentPane().add(button_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(ManPriMessage.class.getResource("/Application/pic3.jpg")));
		lblNewLabel.setBounds(0, 0, 567, 305);
		frame.getContentPane().add(lblNewLabel);
	}
}
