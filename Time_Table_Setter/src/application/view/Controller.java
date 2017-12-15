package application.view;

import java.io.IOException;
import java.util.ArrayList;

import application.Main;
import application.model.Class_Info;
import application.model.DFS;
import application.util.Parser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Controller {
	private ArrayList<Class_Info> Class_List = new ArrayList<Class_Info>();
	//private Class_Info[] Class_List;
	private Class_Info[] Class_L;
	private ArrayList<String> Class_Num_List = new ArrayList<String>();
	private ArrayList<String> Class_Time_List = new ArrayList<String>();
	private ArrayList<Class_Info> Table_Set_Class_List = new ArrayList<Class_Info>();
	private ArrayList<String> E = new ArrayList<String>();
	private Parser par = new Parser();
	private DFS dfs = new DFS();
	private int creditlowlimit = -1;
	private int creditupplimit = -1;
	private int numOfclass = -1;
	private int numOfday = -1;
	private ArrayList<Class_Info> Class_Data;
	private Stage selectStage;
	private boolean isok = false;


	private Main mainApp;
	@FXML private ListView<String> MyTTList;	
	@FXML private TextField txtAddItem; 
	@FXML private Button Select;
	@FXML private Button conditionbutton;
	@FXML private GridPane grid;
	@FXML private Label lab1;
	@FXML private Label[][] labb;
	@FXML private CheckBox creditlower;
	@FXML private CheckBox creditupper;
	@FXML private CheckBox choicenumOfday;
	@FXML private CheckBox numofclassmin;
	@FXML private TextField creditlowertext;
	@FXML private TextField credituppertext;
	@FXML private TextField numofclassmintext;
	@FXML private ComboBox<String> choicenum;
	@FXML private ObservableList<String> listItems;
	ObservableList<String> list; 
	ObservableList<String> numlist = FXCollections.observableArrayList("1", "2", "3", "4", "5");
	
	Select_Class_Set_Controller selectcontroller;

	@FXML
	public void initialize() {		

		labb = new Label[5][9];

		for(int i=0;i<5;i++) 
			for(int j=0;j<9;j++) 
				labb[i][j] = new Label("");

		for(int i=1;i<6;i++)
			for(int j=1;j<10;j++)
				grid.add(labb[i-1][j-1], i, j);

		lab1 = new Label();
		choicenum.setItems(numlist);
	}



	public void buttonAction(ActionEvent event) {
		Table_Set_Class_List.clear();
		ObservableList<String> names;
		names = MyTTList.getSelectionModel().getSelectedItems();
		int timetablesetnum;
		String name = names.toString();
		name = name.substring(1,name.length()-1);

		try {
			timetablesetnum = Integer.parseInt(name.substring(name.lastIndexOf("")-2, name.length()).trim());
			//ex) 시간표  10에서 10을 추출
		}catch(Exception e) {
			System.out.println("시간표를 선택하세요");
			return;
		}

		System.out.println(name);

		ArrayList<String> temp = new ArrayList<String>();

		temp = par.GET(Class_Time_List.get(timetablesetnum).toString());
		String[] aaa = Class_Num_List.get(timetablesetnum).split(",");;

		for(int i=0;i<aaa.length;i++)
		{
			for(int j=0;j<Class_List.size();j++)
			{
				if(aaa[i].compareTo(Class_List.get(j).getClassNum())==0) {
					Table_Set_Class_List.add(Class_List.get(j));
					break;
				}
			}
		}

		int row = 0;
		int col = 0;
		int creditSum = 0;


		for(int i=0;i<5;i++)
			for(int j=0;j<9;j++)
				labb[i][j].setText("");


		for(int i=0;i<Table_Set_Class_List.size();i++) {
			creditSum += Table_Set_Class_List.get(i).getCredit();
			temp = par.GET(Table_Set_Class_List.get(i).getDayInfo());
			//System.out.println(temp.get(0).substring(0, 1));
			for(int j=0;j<temp.size();j++) {
				if(temp.get(j).substring(0,1).compareTo("월") == 0)
					row = 1;
				else if(temp.get(j).substring(0,1).compareTo("화") == 0) 
					row = 2;
				else if(temp.get(j).substring(0,1).compareTo("수") == 0) 
					row = 3;
				else if(temp.get(j).substring(0,1).compareTo("목") == 0) 
					row = 4;
				else 
					row = 5;

				int tmp = Integer.parseInt(temp.get(j).substring(1));
				switch(tmp) {
				case 1:
					col = 1;
					break;
				case 2:
					col = 2;
					break;
				case 3:
					col = 3;
					break;
				case 4:
					col = 4;
					break;
				case 5:
					col = 5;
					break;
				case 6:
					col = 6;
					break;
				case 7:
					col = 7;
					break;
				case 8:
					col = 8;
					break;
				case 9:
					col = 9;
					break;
				};
				labb[row-1][col-1].setText(Table_Set_Class_List.get(i).getClassName()+"\n"+Table_Set_Class_List.get(i).getProfessor());
				//labb[row-1][col-1].setStyle("-fx-background-color: red;");
			}
		}
		System.out.println("학점 총점  = " + creditSum);
	}


	@FXML
	public void Print(ActionEvent event) throws Exception{
		
		try {
		Class_List = selectcontroller.getClassList();
		}catch(Exception e){
			System.out.println("수업을 선택해주세요");
			return;
		}
		
		for(int i=0;i<Class_List.size();i++)
			System.out.println(Class_List.get(i).getClassName());
			
//		for(int i=0;i<Class_List.length;i++) {
//			Class_List[i].time = new ArrayList<String>();
//			Class_List[i].time = par.GET(Class_List[i].dayInfo);
//		}
		//		Stage selectStage = new Stage();
		//		Parent Select = FXMLLoader.load(getClass().getResource("view/Select_Class_Set.fxml"));
		//        Scene scene = new Scene(Select);
		//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		//        selectStage.setScene(scene);
		//        selectStage.show();


		E.clear();
		Class_Num_List.clear();
		Class_Time_List.clear();

		String CurrentSum = "";
		String CurrentTime = "";

		dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List);

		for(int i=0;i<Class_Num_List.size();i++)
			E.add("시간표 " + Integer.toString(i));

		list = FXCollections.observableArrayList(E);
		MyTTList.setItems(list);
		Select.setDisable(false);
	}


	@FXML public void Credit_Lower_Check() {
		if(creditlower.isSelected()) {
			creditlowertext.setDisable(false);
			conditionbutton.setDisable(false);
		}
		else
			creditlowertext.setDisable(true);
	}

	@FXML public void Credit_Upper_Check() {
		if(creditupper.isSelected()) {
			credituppertext.setDisable(false);
			conditionbutton.setDisable(false);
		}
		else
			credituppertext.setDisable(true);
	}

	@FXML public void Choice_Num_Chcek() {
		if(choicenumOfday.isSelected()) {
			choicenum.setDisable(false);
			conditionbutton.setDisable(false);
		}
		else
			choicenum.setDisable(true);
	}

	@FXML public void Num_Of_Class_Check() {
		if(numofclassmin.isSelected()) {
			numofclassmintext.setDisable(false);
			conditionbutton.setDisable(false);
		}
		else
			numofclassmintext.setDisable(true);
	}

	public void comboChange(ActionEvent event) {
		lab1.setText(choicenum.getValue());
	}


	@FXML public void Condition_Print(){
		creditlowlimit = -1;
		creditupplimit = -1;
		numOfclass = -1;

		if(creditlower.isSelected()) {
			try {
				creditlowlimit = Integer.parseInt(creditlowertext.getText());

			}catch(Exception e) {
				System.out.println("creditlowertext is null");
				return;
			}
		}

		if(creditupper.isSelected()) {
			try {
				creditupplimit = Integer.parseInt(credituppertext.getText());
			}catch(Exception e) {
				System.out.println("credituppertext is null");
				return;
			}
		}

		if(choicenumOfday.isSelected()) {
			try {
				numOfday = Integer.parseInt(choicenum.getValue());
				//System.out.println(numOfday);
			}catch(Exception e) {
				System.out.println("combobox is not selected");
				return;
			}
		}

		if(numofclassmin.isSelected()) {
			try {
				numOfclass= Integer.parseInt(numofclassmintext.getText());
			}catch(Exception e) {
				System.out.println("numofclassmintext is null");
				return;
			}
		}

		E.clear();
		Class_Num_List.clear();
		Class_Time_List.clear();
		boolean[] chk = new boolean[5];
		for(int i=0;i<5;i++)
			chk[i] = false;


		System.out.println(creditlowlimit);
		if((creditlower.isSelected()) && !creditupper.isSelected() && !choicenumOfday.isSelected() && !numofclassmin.isSelected() ) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,24);
			System.out.println("1");
		}

		else if (!creditlower.isSelected() && creditupper.isSelected() && !choicenumOfday.isSelected() && !numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,0,creditupplimit);
			System.out.println("2");
		}
		else if (creditlower.isSelected() && creditupper.isSelected() && !choicenumOfday.isSelected() && !numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit);
			System.out.println("3");
		}
		else if(!creditlower.isSelected() && !creditupper.isSelected() && choicenumOfday.isSelected() && !numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,numOfday,chk);
			System.out.println("4");
		}
		else if((creditlower.isSelected()) && (!creditupper.isSelected()) && (choicenumOfday.isSelected())&&(!numofclassmin.isSelected())) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,24,numOfday,chk,0);
			System.out.println("5");
		}
		else if((!creditlower.isSelected()) && (creditupper.isSelected()) && (choicenumOfday.isSelected())&&(!numofclassmin.isSelected())) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,0,creditupplimit,numOfday,chk,0);
			System.out.println("6");
		}
		else if(creditlower.isSelected() && creditupper.isSelected() && choicenumOfday.isSelected()&&(!numofclassmin.isSelected())) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit,numOfday,chk,0);
			System.out.println("7");
		}
		else if(creditlower.isSelected() && creditupper.isSelected() && choicenumOfday.isSelected()&& numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit,numOfday,chk,numOfclass);
			System.out.println("8");
		}
		else if (!creditlower.isSelected() && creditupper.isSelected() && !choicenumOfday.isSelected() && numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,0,creditupplimit,numOfclass);
			System.out.println("9");
		}
		else if (creditlower.isSelected() && !creditupper.isSelected() && !choicenumOfday.isSelected() && numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,24,numOfclass);
			System.out.println("10");
		}
		else if (creditlower.isSelected() && creditupper.isSelected() && !choicenumOfday.isSelected() && numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit,numOfclass);
			System.out.println("11");
		}
		else if (!creditlower.isSelected() && !creditupper.isSelected() && !choicenumOfday.isSelected() && numofclassmin.isSelected()) {
			dfs.dfs(Class_List,0,"","",Class_Num_List,Class_Time_List,numOfclass);
			System.out.println("12");
		}
		else {
			System.out.println("error");
			return;
		}


		for(int i=0;i<Class_Num_List.size();i++)
			E.add("시간표 " + Integer.toString(i));

		list = FXCollections.observableArrayList(E);
		MyTTList.setItems(list);
		Select.setDisable(false);
	}


	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		Class_List = mainApp.getDATA();
	}


	public void setClassInfo(ArrayList<Class_Info> classinfo) {
		this.Class_List = classinfo;
	}
	

	public void testbutton(ActionEvent event) throws IOException {
		selectStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Select_Class_Set.fxml"));
		Parent Select = (Parent)loader.load();
		Scene scene = new Scene(Select);
		selectStage.setScene(scene);
		selectStage.show();

		selectcontroller = (Select_Class_Set_Controller)loader.getController();
		selectcontroller.setMainApp(mainApp);
		//controller.setClassInfo(Class_List);
		selectcontroller.setSelectStage(selectStage);
		//selectcontroller.setController(this);
		
		isok = selectcontroller.isOkClicked();
		//controller.setClassInfo(Class_List);
		//controller.setClassData(Class_Data);
	}
	
	public ArrayList<Class_Info> getClassList() {
		return Class_List;
	}


}

