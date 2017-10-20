package Application;

import javax.swing.JFrame;

import com.zrgj.bickrental.entity.Admin;
import com.zrgj.bickrental.entity.Bike;
import com.zrgj.bickrental.entity.User;

public class TempData {

  public static String priId; 
  public static String aid1;
  public static String aid2;
  public static Admin admin;
  public static User user;
  
  

  //记录被借走的自行车
  public static Bike bike;
  //传递用车时间
  public static String starTimeLabel;
  public static long totalTime;
  //记录借车时间
  public static String boroTime;
  //记录还车的时间
  public static String returnTime;
  //记录应付的费用
  public static String borPay;
  //记录被借走自行车的ID
 // public static String broBikeID;
  
   
  public static String showEndTime;
  public static JFrame Login_frame;

  

}
