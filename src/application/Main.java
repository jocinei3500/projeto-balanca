package application;

//import java.beans.Statement;
import java.io.IOException;

//import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/MainView.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.show();
			stage.setTitle("Sistema de Pesagem - Bresola Terraplanagem e Pavimentação");
			stage.setMaximized(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		launch(args);
	}

}
