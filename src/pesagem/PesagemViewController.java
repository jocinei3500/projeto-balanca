package pesagem;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PesagemViewController implements Initializable {
	private SerialPort comPort;

	@FXML
	private Label lbPesoAtual;
	
	@FXML
	private Label lbPesoTara;
	
	@FXML
	private Label lbPesoBruto;
	
	public void openComport() {
		comPort = SerialPort.getCommPort("COM2"); // Altere para a sua porta
		comPort.setBaudRate(9600);
		if (comPort.openPort()) {
			startReading();
		} else {
			// outputArea.appendText("Erro ao abrir a porta!\n");
		}
	}

	public void closeComport() {
		if (comPort != null && comPort.isOpen()) {
			comPort.closePort();
		}

	}
	private void startReading() {
		Thread thread = new Thread(() -> {
			comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
			try (Scanner scanner = new Scanner(comPort.getInputStream())) {
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					Platform.runLater(() -> lbPesoAtual.setText(removeZeros(line)));
					// Platform.runLater(() -> txtNumber1.setText(removeZeros(line)));

				}
			} catch (Exception e) {
				// Platform.runLater(() -> outputArea.appendText("Erro ao ler: " +
				// e.getMessage() + "\n"));
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	public static String removeZeros(String number) {
		// Remove todos os caracteres não numéricos e zeros à esquerda
		String cleanedNumber = number.replaceAll("[^0-9]", "").replaceFirst("^0+(?!$)", "");

		// Se o resultado for uma string vazia, retorna "0"
		return cleanedNumber.isEmpty() ? "0" : cleanedNumber;
	}
	
	public void calculaPesoLiquido() {
		double peso_bruto, peso_tara;
		
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		openComport();

	}

}
