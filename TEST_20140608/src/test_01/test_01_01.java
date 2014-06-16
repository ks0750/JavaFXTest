package test_01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class test_01_01 extends Application {
	// JavaFX�A�v���P�[�V�����N�����ɃR�[���o�b�N����郁�\�b�h

	
	public static Connection connection = null;
	private static test_01_01 instance; //���̃N���X�̃C���X�^���X
	
	@Override
	public void start(Stage stage) throws Exception {

	// ��ʂ̃^�C�g����ݒ�

	stage.setTitle("TESTFORM");

	// FXML�t�@�C�������[�h
	// hello.fxml �̓ǂݍ���
    Parent root = FXMLLoader.load(getClass().getResource("/TESTFX.fxml"));

	

	// �\�����e��ݒ�
 // Scene �̍쐬�E�o�^
    Scene scene = new Scene(root);
    stage.setScene(scene);

	
	//stage.setScene(new Scene(root,300,250));

	// ��ʕ\��

	stage.show();

	}

	
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName("org.sqlite.JDBC");

		
		//Connection connection = null;
	      try {
	    	 connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\test.db");
	         Statement statement = connection.createStatement();
	         statement.setQueryTimeout(30); // set timeout to 30 sec.

	         ResultSet results = statement.executeQuery("select * from t_test00");
	         while (results.next()) {
	            //System.out.println("name = " + results.getString("name"));
	            //System.out.println("id = " + results.getInt("id"));
	         }
	      } catch (SQLException e) {
	         System.err.println(e.getMessage());
	      } finally {
	         try {
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            System.err.println(e);
	         }
	      }

		
		
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		//System.out.println("Hello Java World !");

		Application.launch(args);
	}
	
	
	public ResultSet getRs()
	{
		ResultSet results = null;
		
		try {
		connection = DriverManager.getConnection("jdbc:sqlite:C:\\SQLite\\test.db");
        Statement statement = connection.createStatement();
        
        results = statement.executeQuery("select * from T_Data"); //�̂��R�R���ςɂ���
		} catch (SQLException e) {
	         System.err.println(e.getMessage());
	      } finally {
	         try {
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            System.err.println(e);
	         }
	      }
		
		return results;
	}
	
	
	public static test_01_01 getInstance()
	{
		test_01_01 instance = new test_01_01();
		return instance;
	}
	
	
	
	

}
