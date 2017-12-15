package application.view;

import java.util.ArrayList;

import application.Main;
import application.model.Class_Info;
import application.model.Table_model;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Select_Class_Set_Controller {
	private ArrayList<Class_Info> Class_List = new ArrayList<Class_Info>();
	//private Class_Info[] Class_List;
	private ArrayList<Class_Info> Class_L = new ArrayList<Class_Info>();
	private Stage selectStage;
	private Main mainApp;
	private Controller controller;
	private boolean okClicked = false;
//	private ArrayList<String> Class_List2 = new ArrayList<String>();
//	private ArrayList<String> Class_DB_List = new ArrayList<String>();
//	private ArrayList<String> Selected_Class_List = new ArrayList<String>();
//	private Parser par = new Parser();
//	private Stage dialogStage;
//	private ArrayList<Class_Info> Class_Data;
	
	@FXML private ObservableList<Class_Info> listItems;
	@FXML private TableView<Table_model> Class_DB;
	@FXML private TableView<Table_model> Selected_Class;
	@FXML private TableColumn<Table_model, String> num;
	@FXML private TableColumn<Table_model, Integer> grd;
	@FXML private TableColumn<Table_model, String> sdv;
	@FXML private TableColumn<Table_model, String> name;
	@FXML private TableColumn<Table_model, String> prof;
	@FXML private TableColumn<Table_model, Integer> crd;
	@FXML private TableColumn<Table_model, String> time;
	@FXML private TableColumn<Table_model, String> selnum;
	@FXML private TableColumn<Table_model, Integer> selgrd;
	@FXML private TableColumn<Table_model, String> selsdv;
	@FXML private TableColumn<Table_model, String> selname;
	@FXML private TableColumn<Table_model, String> selprof;
	@FXML private TableColumn<Table_model, Integer> selcrd;
	@FXML private TableColumn<Table_model, String> seltime;
	@FXML private Button addbutton;
	@FXML private Button deletebutton;
	
	
	ObservableList<Class_Info> classdblist;
	ObservableList<Class_Info> SelectClassList;
	
	
	ObservableList<Table_model> myselList = FXCollections.observableArrayList();
	ObservableList<Table_model> myList = FXCollections.observableArrayList();

	@FXML
	public void initialize()
	{
		num.setCellValueFactory(cellData->cellData.getValue().getClassNum());
		grd.setCellValueFactory(cellData->cellData.getValue().getGrade().asObject());
		sdv.setCellValueFactory(cellData->cellData.getValue().getSubdiv());
		name.setCellValueFactory(cellData->cellData.getValue().getClassName());
		prof.setCellValueFactory(cellData->cellData.getValue().getProfessor());
		crd.setCellValueFactory(cellData->cellData.getValue().getCredit().asObject());
		time.setCellValueFactory(cellData->cellData.getValue().getDayInfo());
		
		selnum.setCellValueFactory(cellData->cellData.getValue().getClassNum());
		selgrd.setCellValueFactory(cellData->cellData.getValue().getGrade().asObject());
		selsdv.setCellValueFactory(cellData->cellData.getValue().getSubdiv());
		selname.setCellValueFactory(cellData->cellData.getValue().getClassName());
		selprof.setCellValueFactory(cellData->cellData.getValue().getProfessor());
		selcrd.setCellValueFactory(cellData->cellData.getValue().getCredit().asObject());
		seltime.setCellValueFactory(cellData->cellData.getValue().getDayInfo());
		
		Class_DB.setItems(myList);
		//Class_DB.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		addbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				for(int i=0;i<Selected_Class.getItems().size();i++) {
					String a = Class_DB.getSelectionModel().selectedItemProperty().getValue().getClassNum().get();
					String b = Selected_Class.getItems().get(i).getClassNum().get();
					if(a == b)
						return;
				}
				
				Selected_Class.getItems().add(Class_DB.getSelectionModel().selectedItemProperty().getValue());
				
				for(int i=0;i<Selected_Class.getItems().size();i++) {
					System.out.println(Selected_Class.getItems().get(i).getClassName());
				}
			}	
		});
		
		deletebutton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				Selected_Class.getItems().remove(Selected_Class.getSelectionModel().getSelectedIndex());
				
				for(int i=0;i<Selected_Class.getItems().size();i++) {
					System.out.println(Selected_Class.getItems().get(i).getClassName());
				}
			}
		});
			
		
	}
		
	
	public void addButtonAction(ActionEvent event) {
		String num;
		int grade;
		String subdiv;
		String name;
		String prof;
		int crd;
		String time;
		
		for(int i=0;i<Selected_Class.getItems().size();i++) {
			num = Selected_Class.getItems().get(i).getClassNum().get();
			grade = Selected_Class.getItems().get(i).getGrade().get();
			subdiv = Selected_Class.getItems().get(i).getSubdiv().get();
			name = Selected_Class.getItems().get(i).getClassName().get();
			prof = Selected_Class.getItems().get(i).getProfessor().get();
			crd = Selected_Class.getItems().get(i).getCredit().get();
			time = Selected_Class.getItems().get(i).getDayInfo().get();
			Class_Info temp = new Class_Info(num,grade,subdiv,name,prof,crd,time);
			Class_L.add(temp);
			//Class_L[i] = new Class_Info(num,name,prof,crd,time);
			//System.out.println(Class_L[i].dayInfo);
		}

		okClicked = true;
		selectStage.close();
	}

	public void deleteButtonAction(ActionEvent event) {
		selectStage.close();
	}


	public void setClassInfo(ArrayList<Class_Info> classinfo) {
		this.Class_List = classinfo;
	}


	public ArrayList<Class_Info> getClassList() {
		return Class_L;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setSelectStage(Stage selectStage) {
		this.selectStage = selectStage;
	}
	 
	 public void setMainApp(Main mainApp) {
	        this.mainApp = mainApp;

	        // 테이블에 observable 리스트 데이터를 추가한다.
	        Class_List = mainApp.getDATA();
	        
	        for(int i=0;i<Class_List.size();i++) {
				myList.add(new Table_model(new SimpleStringProperty(Class_List.get(i).getClassNum()), new SimpleIntegerProperty(Class_List.get(i).getGrade()), new SimpleStringProperty(Class_List.get(i).getSubdiv()), new SimpleStringProperty(Class_List.get(i).getClassName()),new SimpleStringProperty(Class_List.get(i).getProfessor()),new SimpleIntegerProperty(Class_List.get(i).getCredit()),new SimpleStringProperty(Class_List.get(i).getDayInfo())));
			}
	    }
	 
//	 public void setController(Controller controller) {
//		 this.controller = controller;
//		 
//		 //Class_List = controller.getClassList();
//	        
//		 for(int i=0;i<Class_List.size();i++) {
//				myList.add(new Table_model(new SimpleStringProperty(Class_List.get(i).getClassNum()), new SimpleIntegerProperty(Class_List.get(i).getGrade()), new SimpleStringProperty(Class_List.get(i).getSubdiv()), new SimpleStringProperty(Class_List.get(i).getClassName()),new SimpleStringProperty(Class_List.get(i).getProfessor()),new SimpleIntegerProperty(Class_List.get(i).getCredit()),new SimpleStringProperty(Class_List.get(i).getDayInfo())));
//			}
//	 }
	 
//	 
//	 public void setClassData(ArrayList<Class_Info> classinfo) {
//			this.Class_Data = classinfo;
//		}
//	
}
