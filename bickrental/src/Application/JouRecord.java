package Application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.zrgj.bickrental.dao.JouRecoDao;
import com.zrgj.bickrental.entity.UseRecord;
import com.zrgj.bickrental.entity.User;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JouRecord {

	JFrame frame1;
	JFrame beforeFrame;
	private JTable table;
	private JouRecoDao dao = new JouRecoDao();
	private User u = TempData.user;

	private String userId = u.getUserId();
	private JButton button;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JouRecord window = new JouRecord();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JouRecord() {
		initialize();
	}
	
	public void showInfo(){
		//u =   ;
		table = new JTable();
		
		 List<UseRecord> record;
		 Object[][] data = null;//接收数据，从数据库读出来
		  
			 try {
				  record = dao.queryRecord(userId);
				  
				data =new Object[record.size()][6];
				for(int i = 0;i<record.size();i++){
					UseRecord ur = record.get(i);
					data[i][0]= ur.getJourId();
					data[i][1]= ur.getBikeId();
					data[i][2]= ur.getBroTime();
					data[i][3]=ur.getRetTime();
					data[i][4]=ur.getUseTime();
					data[i][5]=ur.getPay();
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			table.setModel(new DefaultTableModel(
				data,
				new String[] {
					"行程编号",  "自行车编号", "借车时间", "还车时间", "使用时间", "消费金额 .￥"
				}
			));
			
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(70);
			table.getColumnModel().getColumn(2).setPreferredWidth(135);
			table.getColumnModel().getColumn(3).setPreferredWidth(135);
			table.getColumnModel().getColumn(4).setPreferredWidth(60);
			table.getColumnModel().getColumn(5).setPreferredWidth(75);
			
			//table.setColumn(0, 10);
			scrollPane.setViewportView(table);
		
	}
	
	
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
		frame1.setResizable(false);
		frame1.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {		
				showInfo();
			}
		});
		frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(JouRecord.class.getResource("/image/logo.png")));
		frame1.setFont(new Font("宋体", Font.BOLD, 12));
		frame1.getContentPane().setBackground(Color.WHITE);
		frame1.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				dao.jdbc.releaseConn();
				beforeFrame.setLocation(frame1.getLocation());
				beforeFrame.setVisible(true);
				
			}
		});
		frame1.setTitle("我的行程记录");
		frame1.setBounds(100, 100, 575, 454);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 56, 529, 338);
		frame1.getContentPane().add(scrollPane);
		
		button = new JButton("返回");
		button.setFocusPainted(false);
		button.setBackground(Color.WHITE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame1.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame1.dispose();
			}
		});
		button.setBounds(439, 20, 93, 23);
		frame1.getContentPane().add(button);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(JouRecord.class.getResource("/image/journey.png")));
		lblNewLabel.setBounds(210, 7, 94, 43);
		frame1.getContentPane().add(lblNewLabel);
	}
}
