package test_01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

//import javafx.fxml.FXML;

public class common {
	
	//日付妥当性チェック
	public boolean CheckDate(String C_Year,String C_Month,String C_Day)
	{
		String strDate = C_Year + "/" + C_Month + "/" + C_Day;
	    DateFormat format = DateFormat.getDateInstance();
	    	    
	    // 日付/時刻解析を厳密に行うかどうかを設定する。
	    format.setLenient(false);
	    
	    try {
	        format.parse(strDate);
	    	//sdf1.format(cal1.getTime());
	        return true;
	    } catch (Exception e) {
	        return false;
	    }

	}
	
	
	//日付妥当性チェック
		public boolean CheckDateDiff(String C_Year,String C_Month,String C_Day,
				String E_Year,String E_Month,String E_Day)
		{
			String strDate = C_Year + "/" + C_Month + "/" + C_Day;
			String endDate = E_Year + "/" + E_Month + "/" + E_Day;
		    DateFormat format = DateFormat.getDateInstance();
		    	    
		    
		    Calendar calS = Calendar.getInstance();
		    Calendar calE = Calendar.getInstance();
		    
		    calS.set(Integer.valueOf(C_Year),Integer.valueOf(C_Month),Integer.valueOf(C_Day));
		    calE.set(Integer.valueOf(E_Year),Integer.valueOf(E_Month),Integer.valueOf(E_Day));
		    
		    boolean bool1 = calS.before(calE);
		    
		  
		    if (bool1 == false)
		    {
		    	bool1 = calS.equals(calE);
		    }
		    
		    return bool1;
		}
	
	
	//現在日時取得
	public String GetDateTime()
	{
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
    	return sdf.format(date);
	}
	
	
	

	
}
