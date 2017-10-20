package Application;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.zrgj.bickrental.dao.MangerDao;





public class Mangercar {
	private com.zrgj.utils.JDBCUtils jdbc = new com.zrgj.utils.JDBCUtils();
	JFrame frame;
	private JTextField text_3;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JTextField text_1;
	private JTextField text_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mangercar window = new Mangercar();
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
	public Mangercar() {
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
		
		JButton btnNewButton = new JButton("提交");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 MangerDao dao = new MangerDao();
			 try {
				boolean flag1 = dao.updateAddnum(text_2.getText(),Integer.parseInt(text_3.getText()));
			    boolean flag2 = dao.updateMinusnum(text_1.getText(),Integer.parseInt(text_3.getText()));
				if(flag1&&flag2){
					JOptionPane.showMessageDialog(null, "修改成功");
				}
			 } catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
//        List<Object> params = new ArrayList<Object>();        
//         params.add(text_3.getText());
//         params.add(text_2.getText());
// 		 boolean flag;
//		try {
//			
//			flag = jdbc.updateByPreparedStatement(sql, params);
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
// 		jdbc.releaseConn();
 	     
			}
		});
		btnNewButton.setBounds(25, 191, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		text_3 = new JTextField();
		text_3.setBounds(174, 73, 66, 21);
		frame.getContentPane().add(text_3);
		text_3.setColumns(10);
		
		lblNewLabel = new JLabel("数量");
		lblNewLabel.setBounds(137, 76, 47, 15);
		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				JAdminFrame jframe1 = new JAdminFrame();
				jframe1.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(235, 191, 93, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("调入站点");
		lblNewLabel_1.setBounds(25, 30, 54, 15);
		frame.getContentPane().add(lblNewLabel_1);
		
		text_1 = new JTextField();
		text_1.setBounds(89, 27, 66, 21);
		frame.getContentPane().add(text_1);
		text_1.setColumns(10);
		
		text_2 = new JTextField();
		text_2.setBounds(331, 27, 66, 21);
		frame.getContentPane().add(text_2);
		text_2.setColumns(10);
		
		lblNewLabel_3 = new JLabel("调出站点");
		lblNewLabel_3.setBounds(258, 30, 54, 15);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
