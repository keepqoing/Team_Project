package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
	private Class_Info[] Class_List;
	private ArrayList<String> Class_Num_List = new ArrayList<String>();
	private ArrayList<String> Class_Time_List = new ArrayList<String>();
	private ArrayList<Class_Info> Table_Set_Class_List = new ArrayList<Class_Info>();
	private ArrayList<String> E = new ArrayList<String>();
	private Parser par = new Parser();
	private DFS dfs = new DFS();
	private int creditlowlimit = -1;
	private int creditupplimit = -1;
	private int numOfday = -1;
	
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
	@FXML private TextField creditlowertext;
	@FXML private TextField credituppertext;
	@FXML private ComboBox<String> choicenum;
	@FXML private ObservableList<String> listItems;
	ObservableList<String> list; 
	ObservableList<String> numlist = FXCollections.observableArrayList("1", "2", "3", "4", "5");

	@FXML
	public void initialize() {
		Class_List  = new Class_Info[6];
		Class_List[0] = new Class_Info("C0","알고리즘","류관희",3,"W5,W6,H2");
		Class_List[1] = new Class_Info("C1","개발 프로젝트","강재구",2,"F5,F6,F7,F8");
		Class_List[2] = new Class_Info("C2","컴퓨터 구조","전중남",3,"T1,T2,W1");
		Class_List[3] = new Class_Info("C3","미래설계","이재성",1,"W8,W9");
		Class_List[4] = new Class_Info("C4","CPL","문현주",3,"T5,T6,W2");
		Class_List[5] = new Class_Info("C5","HCI","황경순",3,"M2,M3,T7,T8");
		
		for(int i=0;i<Class_List.length;i++) {
			Class_List[i].time = new ArrayList<String>();
			Class_List[i].time = par.GET_TIME(Class_List[i].dayInfo);
		}
		

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
        
        temp = par.GET_TIME(Class_Time_List.get(timetablesetnum).toString());
        String[] aaa = Class_Num_List.get(timetablesetnum).split(",");;

        for(int i=0;i<aaa.length;i++)
        {
        	for(int j=0;j<Class_List.length;j++)
        	{
        		if(aaa[i].compareTo(Class_List[j].classNum)==0) {
        			Table_Set_Class_List.add(Class_List[j]);
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
        	creditSum += Table_Set_Class_List.get(i).credit;
        	temp = par.GET_TIME(Table_Set_Class_List.get(i).dayInfo);
        	//System.out.println(temp.get(0).substring(0, 1));
        	for(int j=0;j<temp.size();j++) {
        		if(temp.get(j).substring(0,1).compareTo("M") == 0)
        			row = 1;
        		else if(temp.get(j).substring(0,1).compareTo("T") == 0) 
        			row = 2;
        		else if(temp.get(j).substring(0,1).compareTo("W") == 0) 
        			row = 3;
        		else if(temp.get(j).substring(0,1).compareTo("H") == 0) 
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
        		labb[row-1][col-1].setText(Table_Set_Class_List.get(i).className+"\n"+Table_Set_Class_List.get(i).professor);
        		//labb[row-1][col-1].setStyle("-fx-background-color: red;");
        	}
        }
        System.out.println("학점 총점  = " + creditSum);
	}
	
	
	@FXML
	public void Print(ActionEvent event) {
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
	
	public void comboChange(ActionEvent event) {
		lab1.setText(choicenum.getValue());
    }


	@FXML public void Condition_Print() {
		creditlowlimit = -1;
		creditupplimit = -1;
		
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
		
		String CurrentSum = "";
		String CurrentTime = "";
		E.clear();
		Class_Num_List.clear();
		Class_Time_List.clear();
		boolean[] chk = new boolean[5];
		for(int i=0;i<5;i++)
			chk[i] = false;
		
		
		System.out.println(creditlowlimit);
		if((creditlower.isSelected()) && (!creditupper.isSelected()) && (!choicenumOfday.isSelected()) ) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,creditlowlimit,24);
			System.out.println("1");
		}

		else if ((!creditlower.isSelected()) && (creditupper.isSelected()) && (!choicenumOfday.isSelected())) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,0,creditupplimit);
			System.out.println("2");
		}
		else if (creditlower.isSelected() && creditupper.isSelected() && (!choicenumOfday.isSelected())) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit);
			System.out.println("3");
		}
		else if((!creditlower.isSelected()) && (!creditupper.isSelected()) && choicenumOfday.isSelected()) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,numOfday,chk);
			System.out.println("4");
		}
		else if((creditlower.isSelected()) && (!creditupper.isSelected()) && (choicenumOfday.isSelected())) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,creditlowlimit,24,numOfday,chk);
			System.out.println("5");
		}
		else if((!creditlower.isSelected()) && (creditupper.isSelected()) && (choicenumOfday.isSelected())) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,0,creditupplimit,numOfday,chk);
			System.out.println("6");
		}
		else if(creditlower.isSelected() && creditupper.isSelected() && choicenumOfday.isSelected()) {
			dfs.dfs(Class_List,0,CurrentSum,CurrentTime,Class_Num_List,Class_Time_List,creditlowlimit,creditupplimit,numOfday,chk);
			System.out.println("7");
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
	
	
}

