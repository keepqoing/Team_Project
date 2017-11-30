package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {
	Class_Info[] A;
	ArrayList<String> B = new ArrayList<String>();
	ArrayList<String> C = new ArrayList<String>();
	ArrayList<Class_Info> E = new ArrayList<Class_Info>();
	Parser par = new Parser();
	
	@FXML private ListView<String> MyTTList;	
	@FXML private TextField txtAddItem; 
	@FXML private Button Select;
	@FXML private TableView<Class_Info> table;
	@FXML private TableColumn<Class_Info, String> Time;
	@FXML private TableColumn<Class_Info, String> Mon;
	@FXML private TableColumn<Class_Info, String> Tue;
	@FXML private TableColumn<Class_Info, String> Wed;
	@FXML private TableColumn<Class_Info, String> Thu;
	@FXML private TableColumn<Class_Info, String> Fri;
	@FXML private GridPane grid;
	@FXML private Label lab;
	@FXML private Label[][] labb;
	
	@FXML public ObservableList<Class_Info> mlist;
	@FXML private ObservableList<String> listItems;
	ObservableList<String> list; 
	
	public void initialize(URL url, ResourceBundle rb) {
		MyTTList.setItems(list);
		MyTTList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}	
	
	public void buttonAction(ActionEvent event) {
		E.clear();
		ObservableList<String> names;
        names = MyTTList.getSelectionModel().getSelectedItems();
        String name = names.toString();
        name = name.substring(1,name.length()-1);
        System.out.println(name);
        int b = Integer.parseInt(name.substring(name.lastIndexOf("")-2, name.length()).trim());
        System.out.println(b);
        //System.out.println(name.substring(name.length()-1, name.length()));
        //System.out.println(C.get(b).toString());
        
        ArrayList<String> temp = new ArrayList<String>();
        
        temp = par.GET_TIME(C.get(b).toString());
        String[] aaa = B.get(b).split(",");;
        
        for(int i=0;i<aaa.length;i++) 
        	System.out.println(aaa[i]);
        
        for(int i=0;i<aaa.length;i++)
        {
        	for(int j=0;j<A.length;j++)
        	{
        		if(aaa[i].compareTo(A[j].classNum)==0) {
        			E.add(A[j]);
        			break;
        		}
        	}
        }
        
        for(int i=0;i<E.size();i++)
        	System.out.println(E.get(i).className);
        
        int row = 0;
        int col = 0;
        
        
        for(int i=0;i<5;i++)
        	for(int j=0;j<9;j++)
        		labb[i][j].setText("");


        //grid.getChildren().clear();
        //grid.setGridLinesVisible(true);

        
        for(int i=0;i<E.size();i++) {
        	temp = par.GET_TIME(E.get(i).dayInfo);
        	System.out.println(temp.get(0).substring(0, 1));
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
        		}
        		//lab = new Label(E.get(i).className+"\n"+E.get(i).professor);
        		//grid.add(lab, row, col);
        		labb[row-1][col-1].setText(E.get(i).className+"\n"+E.get(i).professor);
        		labb[row-1][col-1].setStyle("-fx-background-color: red;");
        	}
        }
	}

	
	public void dfs(Class_Info[] AA,int current, String currentSum, String currentTime) {
		if(current == 6) return;
		
		//이번 원소를 포함시키지 않고 시도========================================
		dfs(AA,current+1,currentSum,currentTime);

		for(int i=0;i<AA[current].time.size();i++) {
			if(currentTime.contains(AA[current].time.get(i)))
				return;
			}
		
		//============================================================
		//이번 원소를 포함시키고 시도===========================================
		if(currentSum == "") {
			currentSum = AA[current].classNum;	
			currentTime = AA[current].dayInfo;
		}
		else {
			currentSum = currentSum + "," + AA[current].classNum;
			currentTime = currentTime + "," + AA[current].dayInfo;
		}

		dfs(AA,current+1,currentSum,currentTime);
		//=============================================================
		
		//마지막에 다시 이번 원소를 빼줘야함
		currentSum.substring(AA[current].classNum.length(),currentSum.length());
		currentTime.substring(AA[current].dayInfo.length(), currentTime.length());
		//System.out.println(E.size());
		
		
		B.add(currentSum);
		C.add(currentTime);
	}
	
	
	@FXML
	public void Print(ActionEvent event) {
		A  = new Class_Info[6];
		A[0] = new Class_Info("C0","알고리즘","류관희",3,"W5,W6,H2");
		A[1] = new Class_Info("C1","개발 프로젝트","강재구",3,"F5,F6,F7,F8");
		A[2] = new Class_Info("C2","컴퓨터 구조","전중남",3,"T1,T2,W1");
		A[3] = new Class_Info("C3","미래설계","이재성",3,"W8,W9");
		A[4] = new Class_Info("C4","CPL","문현주",3,"T5,T6");
		A[5] = new Class_Info("C5","HCI","황경순",3,"M2,M3,T7,T8");
		
		for(int i=0;i<A.length;i++) {
			A[i].time = new ArrayList<String>();
			A[i].time = par.GET_TIME(A[i].dayInfo);
		}
		
		String currentTime = "";
		String currentSum = "";
		dfs(A,0,currentSum,currentTime);
		
		String[] D = new String[B.size()];
		for(int i=0;i<B.size();i++)
			D[i] = "시간표 " + Integer.toString(i);
		
		list = FXCollections.observableArrayList(D);
		MyTTList.setItems(list);
		Select.setDisable(false);
		
		labb = new Label[5][9];
		
		for(int i=0;i<5;i++) 
			for(int j=0;j<9;j++) 
				labb[i][j] = new Label("");
			
		for(int i=1;i<6;i++)
        	for(int j=1;j<10;j++)
        		grid.add(labb[i-1][j-1], i, j);
		
	}
}

