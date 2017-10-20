package Application;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import com.zrgj.bickrental.dao.BroBikeDao;
import com.zrgj.bickrental.dao.JouRecoDao;
import com.zrgj.bickrental.entity.Bike;
import com.zrgj.bickrental.entity.UseRecord;
import com.zrgj.bickrental.entity.User;
import com.zrgj.utils.StringUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BroUI {

	JFrame frame;
	JFrame beforeFrame;
	private JComboBox bikeBox;
	private JComboBox nowBox;
	private JButton queryBtn;
	// private String bikeBoxID;
	private String startStop;// 起点站
	private static final String INITIAL_LABEL_TEXT = "00:00:00 ";
	private boolean flag = true;
	private boolean btn1 = false;

	// 方法调用
	private BroBikeDao dao = new BroBikeDao();
	private User u = TempData.user;
	
	private List<Bike> bike;// 获取站点自行车对象
	
	private String time;
	private JLabel startTimeLabel; // 车的使用时间
	
	
	private JouRecoDao jouRecoDao  = new JouRecoDao();

	


	// 内部计时器
	class TimeCount extends Thread {
		long startTime;
		public TimeCount(long startTime){
			this.startTime = startTime;
		}

		public void run() {
			// 当前时间
			
		

			// 点击确认，记录当前时间（借车）时间格式：yyyy-MM-dd HH:mm:ss
			SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 设置日期格式
			// 记录下借车的时间
			TempData.boroTime = df.format(new Date());
			// JOptionPane.showMessageDialog(null, df.format(new Date()));

			while (flag) {
				long longtime = (System.currentTimeMillis() - startTime) / 1000;
				TempData.totalTime = longtime;
				String time = String.format("%02d:%02d:%02d", longtime / 3600,
						longtime / 60, longtime % 60);
				startTimeLabel.setText(time);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 计算金额
	public void getPay() {
		String pay = null;
		double HH = TempData.totalTime / 3600;
		double mm = (TempData.totalTime - HH * 3600) / 60;
		if (TempData.totalTime != 0 && HH < 1) { // 1元起步
			pay = 1 + "";
		} else if (HH > 1) {
			pay = HH * 1 + mm * (1 / 60) + "";
		}
		TempData.borPay = pay;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BroUI window = new BroUI();
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
	public BroUI() {
		initialize();
	}

	
	
	public  UseRecord unBroRecord(){
		 UseRecord queryRecord = jouRecoDao.queryNotReturnRecord(u.getUserId());
		 return  queryRecord;
	
	}
	
	
	public void checkBro(){
		 if(unBroRecord() != null){
				flag = true;
				
				
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 设置日期格式
				
				
				Date startTime = null;
				try {
	
					startTime = df.parse(unBroRecord().getBroTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(startTime != null){
					long s = startTime.getTime();
					new TimeCount(s).start();
				}		
			
				btn1 = true;
				queryBtn.setEnabled(false);
				bikeBox.setEnabled(false);
				
		 }
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		
		
		frame = new JFrame();
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				checkBro();
			}
		});
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setFont(new Font("宋体", Font.PLAIN, 12));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(BroUI.class.getResource("/image/logo.png")));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());
			}
		});
		frame.setTitle("借车");
		frame.setBounds(100, 100, 440, 454);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel label = new JLabel("当前站");
		label.setFont(new Font("宋体", Font.PLAIN, 16));
		label.setBounds(14, 24, 54, 30);
		frame.getContentPane().add(label);

		nowBox = new JComboBox();
		nowBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bikeBox.removeAllItems();
			}
		});
		nowBox.setModel(new DefaultComboBoxModel(
				new String[] { "明发园站", "软件园站","海西园站", "体育馆站" }));
		nowBox.setBounds(82, 24, 96, 30);
		frame.getContentPane().add(nowBox);

		JLabel lblid = new JLabel("自行车ID");
		lblid.setFont(new Font("宋体", Font.PLAIN, 16));
		lblid.setBounds(110, 74, 68, 30);
		frame.getContentPane().add(lblid);

		

		queryBtn = new JButton("");
		queryBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		queryBtn.setIcon(new ImageIcon(BroUI.class.getResource("/image/confirm_bor.png")));
		queryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (StringUtils.isEmpty((String) bikeBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(frame, "还没选车");
					// bikeBox.setForeground(Color.red);
				} else {
					
					
					
					btn1 = true;
					queryBtn.setEnabled(false);
					bikeBox.setEnabled(false);
					// 获取被借走自行车
					TempData.bike = dao.queryBroBike((String) bikeBox
							.getSelectedItem());
					// 确认借车，开始计时
					flag = true;
					
				
					new TimeCount(System.currentTimeMillis()).start();
					
					SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 设置日期格式
	
				
					try {
						jouRecoDao.addJournal_Borrow_Info(u.getUserId(),bikeBox.getSelectedItem().toString(), df.format(new Date()));
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					// 当前站的自行车数量减少1，当前自行车的表清楚该辆车的记录
					try {
						boolean b = dao.deleteStatBikeNum(startStop);
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						boolean b1 = dao.deleteBike((String) bikeBox
								.getSelectedItem());
						if (b1) {
							JOptionPane.showMessageDialog(frame, "借车成功!");
						} 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		queryBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		queryBtn.setBounds(65, 337, 113, 50);
		frame.getContentPane().add(queryBtn);
		// -----

		JButton overUseBtn = new JButton("");
		overUseBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				frame.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		overUseBtn.setIcon(new ImageIcon(BroUI.class.getResource("/image/end_use.png")));

		overUseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btn1) {
					
					TempData.showEndTime = startTimeLabel.getText();
					
					// 让借车按钮可以再次点击
					bikeBox.setEnabled(true);
					queryBtn.setEnabled(true);
					// 跳转到结算界面
					flag = false;
					TempData.starTimeLabel = startTimeLabel.getText();
					// 调用计算费用的函数
					getPay();
					
					frame.setVisible(false);
					startTimeLabel.setText(INITIAL_LABEL_TEXT);
					
					
					
					
					RetuBikeUI window = new RetuBikeUI();
					window.beforeFrame = beforeFrame;
					window.frame.setLocation(frame.getLocation());
					window.frame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(frame, "请先使用车");
				}

			}
		});
		overUseBtn.setFont(new Font("宋体", Font.PLAIN, 16));
		overUseBtn.setBounds(250, 337, 113, 50);
		frame.getContentPane().add(overUseBtn);

		bikeBox = new JComboBox();
		bikeBox.setBackground(Color.WHITE);
		bikeBox.setBounds(192, 74, 83, 30);
		// 获取bikeID
		// TempData.broBikeID = (String) nowBox.getSelectedItem();
		frame.getContentPane().add(bikeBox);

		startTimeLabel = new JLabel(INITIAL_LABEL_TEXT);
		startTimeLabel.setForeground(Color.ORANGE);
		startTimeLabel.setFont(new Font("宋体", Font.PLAIN, 45));
		startTimeLabel.setBounds(111, 210, 207, 96);
		frame.getContentPane().add(startTimeLabel);

		JLabel label_1 = new JLabel("您的使用时间");
		label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		label_1.setBounds(155, 145, 96, 21);
		frame.getContentPane().add(label_1);

		JButton searchBikeButton = new JButton("搜车");
		searchBikeButton.setFocusPainted(false);
		searchBikeButton.setBackground(Color.WHITE);
		searchBikeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取站点名称
				startStop = (String) nowBox.getSelectedItem();

				// 查找当前站的自行车
				bike = dao.queryBike(startStop);
				
				bikeBox.removeAllItems();
				
				for (int i = 0; i < bike.size(); i++) {
					
						bikeBox.addItem(bike.get(i).getBikeId());

				}
			}
		});
		searchBikeButton.setFont(new Font("宋体", Font.PLAIN, 16));
		searchBikeButton.setBounds(14, 75, 68, 30);
		frame.getContentPane().add(searchBikeButton);

		JButton btnNewButton = new JButton("返回");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(Color.WHITE);
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
				beforeFrame.setVisible(true);
				beforeFrame.setLocation(frame.getLocation());

			}
		});
		btnNewButton.setBounds(321, 10, 93, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		
	}
}
