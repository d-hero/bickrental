package Application;

import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import com.zrgj.bickrental.dao.BroBikeDao;
import com.zrgj.bickrental.dao.JouRecoDao;
import com.zrgj.bickrental.dao.RetBikeDao;
import com.zrgj.bickrental.dao.UserDao;
import com.zrgj.bickrental.entity.Bike;
import com.zrgj.bickrental.entity.Station;
import com.zrgj.bickrental.entity.UseRecord;
import com.zrgj.bickrental.entity.User;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JRadioButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class RetuBikeUI {

    JFrame frame;
    JFrame beforeFrame;
	private JComboBox endStopBox;
	private JButton queryPayBtn;
	private JRadioButton rdbtnYes ;
	private JLabel totalMoneyLabel;
	
	private RetBikeDao dao = new RetBikeDao();
	private JouRecoDao jrdao = new JouRecoDao();
	private User u = TempData.user;
	private Bike bike = TempData.bike;
	private String bikeID,bikecolor;//记录被借走车的新的
	private Station station ;//获取当前站对象
	private String starTimeLabel = TempData.starTimeLabel;//结束时间
	
	
	
	
	// double a1=Double.parseDouble(a);String-->double

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetuBikeUI window = new RetuBikeUI();
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
	public RetuBikeUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RetuBikeUI.class.getResource("/image/logo.png")));
		frame.setFont(new Font("宋体", Font.BOLD, 12));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				
				beforeFrame.setLocation(frame.getLocation());
				beforeFrame.setVisible(true);
				
			}
		});
		frame.setTitle("还车");
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("您的使用时间");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(152, 75, 96, 21);
		frame.getContentPane().add(label);
		
		JLabel endTimeLabel = new JLabel("");
		endTimeLabel.setForeground(Color.ORANGE);
		endTimeLabel.setFont(new Font("宋体", Font.PLAIN, 45));
		endTimeLabel.setBounds(98, 117, 223, 98);
		endTimeLabel.setText(TempData.showEndTime);
		frame.getContentPane().add(endTimeLabel);
		
		JLabel label_2 = new JLabel("当前站");
		label_2.setFont(new Font("宋体", Font.PLAIN, 16));
		label_2.setBounds(14, 23, 54, 39);
		frame.getContentPane().add(label_2);
		
		endStopBox = new JComboBox();
		endStopBox.setModel(new DefaultComboBoxModel(new String[] {"明发园站", "软件园站","海西园站", "体育馆站"}));
		endStopBox.setBounds(81, 23, 96, 30);
		frame.getContentPane().add(endStopBox);
		//
		 rdbtnYes = new JRadioButton("Yes");
		 rdbtnYes.setBackground(Color.WHITE);
		rdbtnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//当前站点对象
				station=dao.getNowStation((String)endStopBox.getSelectedItem());
				
			}
		});
		rdbtnYes.setBounds(186, 23, 54, 30);
		frame.getContentPane().add(rdbtnYes);
		
		JLabel label_3 = new JLabel("付费金额");
		label_3.setFont(new Font("宋体", Font.PLAIN, 16));
		label_3.setBounds(264, 310, 73, 30);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("￥(元)");
		label_4.setBounds(354, 357, 54, 15);
		frame.getContentPane().add(label_4);
		
		queryPayBtn = new JButton("");
		queryPayBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		queryPayBtn.setIcon(new ImageIcon(RetuBikeUI.class.getResource("/image/settleAccounts.png")));
		queryPayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (!rdbtnYes.isSelected()) {
					JOptionPane.showMessageDialog(frame, "请点Yes");
				} else {
					queryPayBtn.setEnabled(false);
					// 获取当前时间（还车时间）
					SimpleDateFormat df = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");// 设置日期格式
					// 记录下借车的时间
					TempData.returnTime = df.format(new Date());
					// 当前站数量+1，表插入新的数据

					JouRecoDao jd = new JouRecoDao();
					UseRecord userRecord = jd
							.queryNotReturnRecord(TempData.user.getUserId());

					bikeID = station.getStatId()
							+ userRecord.getBikeId().substring(3);// 新的ID

					try {
						boolean b1 = dao.addStatBikeNum(station.getStatName());
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						boolean b2 = dao.addBike(bikeID, station.getStatId());
			
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {

						boolean jouInsert = jrdao.addJournal_Return_Info(
								u.getUserId(), TempData.returnTime,
								TempData.starTimeLabel,
								Double.parseDouble(TempData.borPay));

						if (jouInsert) {
							JOptionPane.showMessageDialog(frame, "结算成功");

						} else {
							JOptionPane.showMessageDialog(frame, "结算失败");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// 扣掉用户余额
					UserDao userDao = new UserDao();
					User user = u;
					u.setBalance((-1) * Double.parseDouble(TempData.borPay));
					userDao.updateUserBalance(user);

					totalMoneyLabel.setText(TempData.borPay);
				}
				
			}	
			
		});
		queryPayBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		queryPayBtn.setBounds(81, 252, 110, 50);
		frame.getContentPane().add(queryPayBtn);
		
		totalMoneyLabel = new JLabel("");
		totalMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalMoneyLabel.setFont(new Font("宋体", Font.BOLD, 20));
		totalMoneyLabel.setBackground(Color.WHITE);
		totalMoneyLabel.setBounds(298, 353, 54, 18);
		frame.getContentPane().add(totalMoneyLabel);
		
		JButton button = new JButton("返回");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 15));
		button.setFocusPainted(false);
		button.setBackground(Color.WHITE);
		button.setBounds(295, 13, 113, 27);
		frame.getContentPane().add(button);
		
		
	}
}
