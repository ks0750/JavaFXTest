package test_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB_Parts {

	//データ登録メソッド
	public void DataInsert(String C_Title,String C_Author, String C_Publisher,
							String C_StartPeriod,String C_EndPeriod, String C_Memo,
							String C_Insertdate,Connection connection)
	{
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO 自動生成された catch ブロック
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
	      
	      //SQL実行
	      int  num =statement.executeUpdate(sql);
        } catch (SQLException e) {
			// TODO 自動生成された catch ブロック
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
	
	//SQL文作成
	public String CreateSQL(String C_Title,String C_Author, String C_Publisher,
			String C_StartPeriod,String C_EndPeriod, String C_Memo)
	{
		String Re_SQL = "";
		boolean SearchF = false;
		
		Re_SQL ="select * from T_Data";
		
		//タイトルが入力されているか
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
		
		//著者が入力されているか
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
		
		//出版社が入力されているか
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
		
		
		
		//開始期間が入力されているか
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
		
		//終了期間が入力されているか
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
		
		//メモが入力されているか
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
