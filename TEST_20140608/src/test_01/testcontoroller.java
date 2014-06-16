package test_01;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JOptionPane;



public class testcontoroller implements Initializable{ //implements Initializable
	//@FXML private Button InButton;
	@FXML private Button AppButton;
	//@FXML private TextField t_title;
    @FXML private Label  label1;
    @FXML private Button ReadButton;
    @FXML private TableView<T_Data> T_Table;
    @FXML private TableColumn C_Title;
    @FXML private TableColumn C_Author;
    @FXML private TableColumn C_Publisher;
    @FXML private TableColumn C_StartPeriod;
    @FXML private TableColumn C_EndPeriod;
    @FXML private TableColumn C_Memo;
    @FXML private TableColumn C_InsertDate;
    @FXML private ComboBox<String> S_Year;
    @FXML private ComboBox<String> S_Month;
    @FXML private ComboBox<String> S_Day;
    @FXML private ComboBox<String> E_Year;
    @FXML private ComboBox<String> E_Month;
    @FXML private ComboBox<String> E_Day;
    @FXML private TextField T_Title;
    @FXML private TextField T_Author;
    @FXML private TextField T_Publisher;
    @FXML private TextField T_Memo;
    
   
    public static Connection connection = null;

    

    @FXML
    public void onAppButtonClicked(ActionEvent event) {
    	//this.label1.setText("Hello Java World !");
    	
    	try {
            if (connection != null) {
               connection.close();
            }
         } catch (SQLException e) {
            System.err.println(e);
         }
    	
    	System.exit(0);
    }
    
    @FXML
    //登録
    public void onInsertButtonClicked(ActionEvent event)
    {
    	common T_common = new common();
    	
    	String timet = T_common.GetDateTime(); //現在日時取得
    	boolean RunFlg =true;
    	
    	String Set_S_Year = S_Year.getSelectionModel().getSelectedItem();
    	String Set_S_Month = S_Month.getSelectionModel().getSelectedItem();
    	String Set_S_Day = S_Day.getSelectionModel().getSelectedItem();
    	String Set_E_Year = E_Year.getSelectionModel().getSelectedItem();
    	String Set_E_Month = E_Month.getSelectionModel().getSelectedItem();
    	String Set_E_Day = E_Day.getSelectionModel().getSelectedItem();
    	
    	
    	
    	//開始期間の妥当性チェック
    	if (T_common.CheckDate(Set_S_Year, Set_S_Month, Set_S_Day) == false)
    	{
    		JOptionPane.showMessageDialog(null, "開始期間に存在しない日付が入力されています!!");
    		RunFlg =false;
    	}
    	
    	//終了期間の妥当性チェック
    	if (T_common.CheckDate(Set_E_Year, Set_E_Month, Set_E_Day) == false)
    	{
    		JOptionPane.showMessageDialog(null, "終了期間に存在しない日付が入力されています!!");
    		RunFlg =false;
    	}
    	
    	//開始期間 > 終了期間
    	if (T_common.CheckDateDiff(Set_S_Year, Set_S_Month, Set_S_Day,
    			Set_E_Year, Set_E_Month, Set_E_Day) == false)
		{
    		JOptionPane.showMessageDialog(null, "終了期間が開始期間より前に設定されています!!");
    		RunFlg = false;	
		}
    	
    	//処理開始
    	if (RunFlg == true)
    	{
    		DB_Parts C_DB_P = new DB_Parts();
    		
    		String StartDate = Set_S_Year + "-" + Set_S_Month + "-" + Set_S_Day;
    		String EndDate = Set_E_Year + "-" + Set_E_Month + "-" + Set_E_Day;

    		//データ登録
    		C_DB_P.DataInsert(T_Title.getText(), T_Author.getText(), 
    				T_Publisher.getText(), StartDate, EndDate, T_Memo.getText(), timet,connection);
    	
    		C_DB_P = null;
    		
    		JOptionPane.showMessageDialog(null, "登録しました。");
    	}
    	
    	
    	
    	
    	T_common = null;
    }
    
    @FXML
    //検索
    public void onReadButtonClicked(ActionEvent event) throws ClassNotFoundException {
    	ResultSet results = null; //データ取得用変数
    	//test_01_01 instance = new test_01_01();
    	String Set_S_Year = S_Year.getSelectionModel().getSelectedItem();
    	String Set_S_Month = S_Month.getSelectionModel().getSelectedItem();
    	String Set_S_Day = S_Day.getSelectionModel().getSelectedItem();
    	String Set_E_Year = E_Year.getSelectionModel().getSelectedItem();
    	String Set_E_Month = E_Month.getSelectionModel().getSelectedItem();
    	String Set_E_Day = E_Day.getSelectionModel().getSelectedItem();
    	
    	common T_common = new common();
    	
    	String StartDate = "";
    	//開始期間の妥当性チェック
    	if (T_common.CheckDate(Set_S_Year, Set_S_Month, Set_S_Day) )
    	{
    		StartDate = Set_S_Year + "-" + Set_S_Month + "-" + Set_S_Day;
    	}
    	
    	String EndDate = "";
    	//終了期間の妥当性チェック
    	if (T_common.CheckDate(Set_E_Year, Set_E_Month, Set_E_Day))
    	{
    		EndDate = Set_E_Year + "-" + Set_E_Month + "-" + Set_E_Day;
    	}
    	
    	
    	T_Table.getItems().clear(); //テーブルビュー初期化
    	
    	C_Title.setCellValueFactory(new PropertyValueFactory<T_Data, String>("Title"));
    	C_Author.setCellValueFactory(new PropertyValueFactory<T_Data, String>("Author"));
    	C_Publisher.setCellValueFactory(new PropertyValueFactory<T_Data, String>("Publisher"));
    	C_StartPeriod.setCellValueFactory(new PropertyValueFactory<T_Data, String>("StartPeriod"));
    	C_EndPeriod.setCellValueFactory(new PropertyValueFactory<T_Data, String>("EndPeriod"));
    	C_Memo.setCellValueFactory(new PropertyValueFactory<T_Data, String>("Memo"));
    	C_InsertDate.setCellValueFactory(new PropertyValueFactory<T_Data, String>("InsertDate"));
    	
    	
    	
    	// サンプルデータを1行追加
        //T_Table.getItems().add(new T_Data("TEST本", "自分", "自分","2014-06-11","2014-06-11","メモ欄","2014-06-11"));

        Class.forName("org.sqlite.JDBC");
        
        try {
	        //Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\test.db");
	        Statement statement = connection.createStatement();
	        
	        //String sql = "Insert into T_Data Values('テスト2','TEST２','シュッパシャ','2014-05-12','2014-06-11','メモ欄','2014-06-12')";
	        //int  num =statement.executeUpdate(sql);
	        
	        DB_Parts C_DB_P = new DB_Parts();
	        //results = statement.executeQuery("select * from T_Data"); //のちココを可変にする
	        results = statement.executeQuery(C_DB_P.CreateSQL(T_Title.getText(), T_Author.getText(), 
    				T_Publisher.getText(), StartDate, EndDate, T_Memo.getText())); //のちココを可変にする

	        try {
				while (results.next())
				{
					String G_Title=results.getString("Title");
					String G_Author=results.getString("Author");
					String G_Publisher=results.getString("Publisher");
					String G_StartPeriod=results.getString("StartPeriod");
					String G_EndPeriod=results.getString("EndPeriod");
					String G_Memo=results.getString("Memo");
					String G_InsertDate=results.getString("InsertDate");
					
					//データの追加
					T_Table.getItems().add(new T_Data(G_Title, G_Author, G_Publisher,G_StartPeriod,G_EndPeriod,G_Memo,G_InsertDate));
				}
	        } catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
	         //try {
		     //       if (connection != null) {
		     //          connection.close();
		     //       }
		     //    } catch (SQLException e) {
		     //       System.err.println(e);
		     //   }
        
        
		
		}
        
    }
    
    //初期処理
    public void initialize(URL url, ResourceBundle rb) {
    	try {
    		//DB接続
	        connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\test.db");
    	} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
    	}
    	
        //各コンボボックスクリア
        S_Year.getItems().clear(); //開始年
        S_Month.getItems().clear(); //開始月
        S_Day.getItems().clear(); //開始日
        E_Year.getItems().clear(); //終了年
        E_Month.getItems().clear(); //終了年
        E_Day.getItems().clear(); //終了年
        
        T_Table.getItems().clear(); //テーブルビュー初期化
        
        Calendar cal = Calendar.getInstance();
	    int year = cal.get(Calendar.YEAR); //現在の年
        
	    //空挿入
	    S_Year.getItems().addAll("");
	    S_Month.getItems().addAll("");
	    S_Day.getItems().addAll("");
	    E_Year.getItems().addAll("");
	    E_Month.getItems().addAll("");
	    E_Day.getItems().addAll("");
	    
	    //現在年+10作成
	    for (int n = 0; n <= 10; n++)
	    {
	    	S_Year.getItems().addAll(String.valueOf(year + n));
	    	E_Year.getItems().addAll(String.valueOf(year + n));
	    }
	    
        //月作成
	    for (int n=1; n <= 12; n++)
	    {
	    	//S_Month.getItems().addAll(String.valueOf(n));
	    	S_Month.getItems().addAll(String.format("%1$02d",n));
	    	//E_Month.getItems().addAll(String.valueOf(n));
	    	E_Month.getItems().addAll(String.format("%1$02d",n));
	    }
        
        //日作成(とりあえず31日)
	    for (int n= 1; n<= 31; n++)
	    {
	    	S_Day.getItems().addAll(String.format("%1$02d",n));
	    	//S_Day.getItems().addAll(String.valueOf(n));
	    	E_Day.getItems().addAll(String.format("%1$02d",n));
	    	
	    }
        
        
    }

    
}
