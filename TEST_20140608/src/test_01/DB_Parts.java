package test_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB_Parts {

	//�f�[�^�o�^���\�b�h
	public void DataInsert(String C_Title,String C_Author, String C_Publisher,
							String C_StartPeriod,String C_EndPeriod, String C_Memo,
							String C_Insertdate,Connection connection)
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO �����������ꂽ catch �u���b�N
			e1.printStackTrace();
		}
		
		//connection = null;
		
        
        try {
	        //connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\test.db");
	        Statement statement = connection.createStatement();
	        
	        
	      String sql = "Insert into T_Data Values(' " + C_Title + "',";
	      sql += "'" + C_Author + "','" + C_Publisher + "',";
	      sql += "'" + C_StartPeriod + "','" + C_EndPeriod + "',";
	      sql += "'" + C_Memo + "','" + C_Insertdate + "')";
	      
	      //SQL���s
	      int  num =statement.executeUpdate(sql);
        } catch (SQLException e) {
			// TODO �����������ꂽ catch �u���b�N
			e.printStackTrace();
		}finally {
	         //try {
		     //       if (connection != null) {
		     //          connection.close();
		     //       }
		     //    } catch (SQLException e) {
		     //       System.err.println(e);
		     //    }
		}
	}
	
	//SQL���쐬
	public String CreateSQL(String C_Title,String C_Author, String C_Publisher,
			String C_StartPeriod,String C_EndPeriod, String C_Memo)
	{
		String Re_SQL = "";
		boolean SearchF = false;
		
		Re_SQL ="select * from T_Data";
		
		//�^�C�g�������͂���Ă��邩
		if (C_Title != null && C_Title.length() > 0)
		{
			if (SearchF == false)
			{
				Re_SQL += " where ";
				SearchF=true;
			} else {
				Re_SQL += " and";
			}
			
			Re_SQL += " Title like '%" + C_Title + "%'";
		}
		
		//���҂����͂���Ă��邩
		if (C_Author != null && C_Author.length() > 0)
		{
			if (SearchF == false)
			{
				Re_SQL += " where ";
				SearchF=true;
			} else {
				Re_SQL += " and";
			}
			
			Re_SQL += " Author like '%" + C_Author + "%'";
		}
		
		//�o�ŎЂ����͂���Ă��邩
		if (C_Publisher != null && C_Publisher.length() > 0)
		{
			if (SearchF == false)
			{
				Re_SQL += " where ";
				SearchF=true;
			} else {
				Re_SQL += " and";
			}
			
			Re_SQL += " Publisher like '%" + C_Publisher + "%'";
		}
		
		
		
		//�J�n���Ԃ����͂���Ă��邩
		if (C_StartPeriod != null && C_StartPeriod.length() > 0)
		{
			if (SearchF == false)
			{
				Re_SQL += " where ";
				SearchF=true;
			} else {
				Re_SQL += " and";
			}
			
			Re_SQL += " date(StartPeriod) >= '" + C_StartPeriod + "'";
		}
		
		//�I�����Ԃ����͂���Ă��邩
		if (C_EndPeriod != null && C_EndPeriod.length() > 0)
		{
			if (SearchF == false)
			{
				Re_SQL += " where ";
				SearchF=true;
			} else {
				Re_SQL += " and";
			}
			
			Re_SQL += " date(EndPeriod) <= '" + C_EndPeriod + "'";
		}
		
		//���������͂���Ă��邩
		//if (C_Memo != null && C_Memo.length() > 0)
		//{
		//	if (SearchF != false)
		//	{
		//		Re_SQL += " where ";
		//		SearchF=true;
		//	} else {
		//		Re_SQL += " and";
		//	}
			
		//	Re_SQL += " Memo = '" + C_Memo + "%'";
		//}
		
		JOptionPane.showMessageDialog(null, Re_SQL);
		
		return Re_SQL;
	}
	
	
}
