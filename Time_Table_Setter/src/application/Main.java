package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Time_Table.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("TimeTableSet");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
    	//DBConnection connection = new DBConnection();
		//System.out.println("관리자 여부 : " + connection.isAdmin("admin", "admin"));
	}
}
