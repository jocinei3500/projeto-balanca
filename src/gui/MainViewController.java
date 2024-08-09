package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fazecast.jSerialComm.SerialPort;

import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {
	private SerialPort comport;

    public void openParceiroView() {
    	
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ViewParceiro.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();

            stage.setTitle("Cadastro de Parceiros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void openPesagemView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../pesagem/PesagemView.fxml"));
            Parent root = fxmlLoader.load();
            
            Stage stage = new Stage();
            stage.setTitle("Pesagem Automática");
            stage.setScene(new Scene(root));
      
            //abrindo form em showModal. tipo no Delphi 
            stage.initModality(Modality.APPLICATION_MODAL);
            
            stage.setOnCloseRequest(event->{
            	closeComport();
            });

            stage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeComport() {
    	comport = SerialPort.getCommPort("COM2"); // Altere para a sua porta
		if (comport != null && comport.isOpen()) {
			comport.closePort();
			Alerts.showAlert(null, null, null, null);
		}

	}
    
    @FXML
    private void initialize() {
    }


}
