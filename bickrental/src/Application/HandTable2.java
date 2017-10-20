package Application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

import com.zrgj.bickrental.dao.ApplyDao;
import com.zrgj.bickrental.dao.MangerDao;
import java.awt.Toolkit;

public class HandTable2 {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblNewLabel_1;
	MangerDao dao=new MangerDao();
	ApplyDao dao2=new ApplyDao();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HandTable2 window = new HandTable2();
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
	public HandTable2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("处理调度");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(HandTable2.class.getResource("/image/logo.png")));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("调出站点：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 10, 90, 21);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(Color.ORANGE);
		textField.setEditable(false);
		textField.setBounds(99, 12, 66, 21);
		textField.setOpaque(false);
		String sId;
		try {
			sId = dao.getStatId(TempData.aid1);
			//System.out.println(sId);
			textField.setText(sId);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		textField.setHorizontalAlignment(JTextField.CENTER);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("调入站点：");
		label.setForeground(Color.RED);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		label.setBounds(205, 10, 90, 21);
		frame.getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setForeground(Color.ORANGE);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setOpaque(false);
		String sId2;
		try {
			sId2 = dao.getStatId(TempData.aid2);
			System.out.println(sId2);
			textField_1.setText(sId2);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		textField_1.setHorizontalAlignment(JTextField.CENTER);
		textField_1.setBounds(300, 12, 66, 21);
		frame.getContentPane().add(textField_1);
		
		label_1 = new JLabel("调度数量：");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("宋体", Font.PLAIN, 18));
		label_1.setBounds(98, 59, 90, 21);
		frame.getContentPane().add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.BOLD, 18));
		textField_2.setForeground(Color.YELLOW);
		textField_2.setOpaque(false);
		textField_2.setHorizontalAlignment(JTextField.CENTER);
		textField_2.setColumns(10);
		textField_2.setBounds(187, 61, 81, 21);
		frame.getContentPane().add(textField_2);
		
		btnNewButton = new JButton("确认调度");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num=Integer.valueOf(textField_2.getText());
				if(textField_2.getText().equals("")){
					JOptionPane.showMessageDialog(null, "调度数量不能为空！");
				}else{
				try {
					dao2.deleteTable(TempData.aid1);
					dao2.deleteTable(TempData.aid2);	
					dao.updateMinusnum(textField.getText(), num);
					dao.updateAddnum(textField_1.getText(), num);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"调度成功！");
			}}
		});
		btnNewButton.setForeground(Color.CYAN);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBounds(53, 188, 112, 23);
		frame.getContentPane().add(btnNewButton);
		
		button = new JButton("返回申请单界面");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandTable window = new HandTable();
				window.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		
		button.setForeground(Color.CYAN);
		button.setContentAreaFilled(false);
		button.setBounds(233, 188, 133, 23);
		frame.getContentPane().add(button);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(HandTable2.class.getResource("/Application/pic1.jpg")));
		lblNewLabel_1.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(lblNewLabel_1);
	}

}
