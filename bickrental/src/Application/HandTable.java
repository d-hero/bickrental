package Application;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


import com.zrgj.bickrental.dao.ApplyDao;
import com.zrgj.bickrental.dao.MangerDao;
import com.zrgj.bickrental.entity.ApplyClass;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class HandTable {

	 JFrame frame;
	private JTable table;
	private JLabel lblNewLabel;
	ApplyDao dao=new ApplyDao();
	private JLabel lblNewLabel_1;
	private JTextField textField;
	private JTextField textField_1;
	private JButton button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HandTable window = new HandTable();
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
	public HandTable() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("处理调度");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HandTable.class.getResource("/image/logo.png")));
		frame.setResizable(false);
		frame.setBounds(100, 100, 596, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblNewLabel_1 = new JLabel("选择要处理的表单号（前调出，后调入）：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(10, 252, 254, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("确定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
					if(textField.getText().equals("")||textField_1.getText().equals("")){
						JOptionPane.showMessageDialog(null, "处理单号不能为空！");
					}else{
					TempData.aid1=textField.getText();
					TempData.aid2=textField_1.getText();
					HandTable2 window = new HandTable2();
					window.frame.setVisible(true);
					frame.setVisible(false);}

			}
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(461, 232, 120, 23);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("返回管理界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainManage window = new MainManage();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setForeground(Color.CYAN);
		button.setContentAreaFilled(false);
		button.setBounds(461, 265, 120, 23);
		frame.getContentPane().add(button);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.GREEN);
		textField_1.setColumns(10);
		textField_1.setBounds(350, 255, 80, 21);
		textField_1.setOpaque(false);
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField_1);
		
		textField = new JTextField();
		textField.setForeground(Color.GREEN);
		textField.setBounds(260, 255, 80, 21);
		textField.setOpaque(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 571, 212);
		scrollPane.getUI();
		frame.getContentPane().add(scrollPane);
		
		List<ApplyClass> persons;
		Object[][] data = null;
		try {
			persons =  dao.getMessage();
			data = new Object[persons.size()][5];
			for(int i = 0;i<persons.size();i++){
				ApplyClass a = persons.get(i);
				data[i][0] = a.getaId();
				data[i][1] = a.getStatId();
				data[i][2] = a.getAnum();
				data[i][4] = a.getAtime();
				data[i][3] = a.getAtype();
				//data[i][5] = a.getAstate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table = new JTable();
		
		table.setModel(new DefaultTableModel(
				data,
			new String[] {
				"表单号", "站点号", "申请调度车辆数", "调度方式", "申请时间"
			}
		));
		
	
	//        scrollPane.getViewport().setOpaque(false);//将JScrollPane设置为透明  


		
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(HandTable.class.getResource("/Application/pic2.jpg")));
		lblNewLabel.setBounds(0, 0, 591, 288);
		frame.getContentPane().add(lblNewLabel);
	}
}
