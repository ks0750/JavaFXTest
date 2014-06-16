package test_01;

//import javafx.beans.property.IntegerProperty;
//import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;



public class T_Data {
	private StringProperty Title;
    private StringProperty Author;
    private StringProperty Publisher;
    private StringProperty StartPeriod;
    private StringProperty EndPeriod;
    private StringProperty Memo;
    private StringProperty InsertDate;
	
    
    //�R���X�g���N�^
    public T_Data(String T_Id, String T_Author, String T_Publisher,
    		String T_StartPeriod,String T_EndPeriod,String T_Memo,String T_InsertDate) {
        Title = new SimpleStringProperty(T_Id);
        Author = new SimpleStringProperty(T_Author);
        Publisher = new SimpleStringProperty(T_Publisher);
        StartPeriod = new SimpleStringProperty(T_StartPeriod);
        EndPeriod= new SimpleStringProperty(T_EndPeriod);
        Memo= new SimpleStringProperty(T_Memo);
        InsertDate= new SimpleStringProperty(T_InsertDate);
    }

    //�^�C�g���v���p�e�B
    public StringProperty TitleProperty() {
        return Title;
    }

    //���҃v���p�e�B
    public StringProperty AuthorProperty() {
        return Author;
    }
    
    //�o�ŎЃv���p�e�B
    public StringProperty PublisherProperty() {
        return Publisher;
    }
    
    //�J�n���ԃv���p�e�B
    public StringProperty StartPeriodProperty() {
        return StartPeriod;
    }
    
    //�I�����ԃv���p�e�B
    public StringProperty EndPeriodProperty() {
        return EndPeriod;
    }
    
    //�����v���p�e�B
    public StringProperty MemoProperty() {
        return Memo;
    }
    
    //�o�^���t�v���p�e�B
    public StringProperty InsertDateProperty() {
        return InsertDate;
    }
}
