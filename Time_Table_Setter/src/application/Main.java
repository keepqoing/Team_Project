package application;
	
import java.io.IOException;
import java.util.ArrayList;

import application.model.Class_Info;
import application.model.Class_Infoo;
import application.util.Parser;
import application.view.Controller;
import application.view.Select_Class_Set_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	private Class_Info[] Class_List;
	
	Class_Info_DAO dao = new Class_Info_DAO();
	Class_Infoo classinfo;
	ArrayList<Class_Info> DATA = new ArrayList<Class_Info>();
	
	String msg = "## 메시지 : ";
	boolean editmode;
	
	Parser par = new Parser();
	
	private ArrayList<Class_Info> Class_Data;
//	private Stage primaryStage;
//	private ObservableList<Class_Info> Class_Data = FXCollections.observableArrayList();
	ArrayList<String> temp = new ArrayList<String>(); 
	
	public void refreshData() {
		DATA = dao.getAll();
		if(DATA != null) {
			for(Class_Info c : DATA) {
				System.out.println(c.getClassNum());
				System.out.println(c.getGrade());
				System.out.println(c.getSubdiv());
				System.out.println(c.getClassName());
				System.out.println(c.getProfessor());
				System.out.println(c.getCredit());
				System.out.println(c.getDayInfo());
				c.setTime(c.getDayInfo());
				String tmp = c.getDayInfo();
				temp = par.GET(tmp);
				for(int i=0;i<temp.size();i++)
					System.out.println(temp.get(i));
			}
		}
	}
	
	public Main() {
		refreshData();
	}
	
	public ArrayList<Class_Info> getDATA() {
		return DATA;
	}
	
	public void setDATA(ArrayList<Class_Info> DATA) {
		this.DATA = DATA;
	}
	
	

		
	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("view/Time_Table.fxml"));
		Parent root = (Parent)loader.load();
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("TimeTableSet");
		primaryStage.show();
		
		Controller controller = (Controller)loader.getController();
		controller.setMainApp(this);
		controller.setClassInfo(DATA);
		
//		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("view/Select_Class_Set.fxml"));
//		Select_Class_Set_Controller controller2 = (Select_Class_Set_Controller)loader2.getController();
//		//controller2.setMainApp(this);
//		controller2.setClassInfo(DATA);
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
    	//DBConnection connection = new DBConnection();
		//System.out.println("관리자 여부 : " + connection.isAdmin("admin", "admin"));
	}
	
	
//	public Class_Info[] getClassList() {
//	return Class_List;
//}
//
//public void setClassList(Class_Info[] classlist) {
//	this.Class_List = classlist;
//}
//
//public ObservableList<Class_Info> getClassData(){
//	return Class_Data;
//}
//
}
