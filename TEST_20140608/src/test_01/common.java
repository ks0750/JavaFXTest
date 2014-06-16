package test_01;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

//import javafx.fxml.FXML;

public class common {
	
	//���t�Ó����`�F�b�N
	public boolean CheckDate(String C_Year,String C_Month,String C_Day)
	{
		String strDate = C_Year + "/" + C_Month + "/" + C_Day;
	    DateFormat format = DateFormat.getDateInstance();
	    	    
	    // ���t/������͂������ɍs�����ǂ�����ݒ肷��B
	    format.setLenient(false);
	    
	    try {
	        format.parse(strDate);
	    	//sdf1.format(cal1.getTime());
	        return true;
	    } catch (Exception e) {
	        return false;
	    }

	}
	
	
	//���t�Ó����`�F�b�N
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
	
	
	//���ݓ����擾
	public String GetDateTime()
	{
		Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
    	return sdf.format(date);
	}
	
	
	

	
}
