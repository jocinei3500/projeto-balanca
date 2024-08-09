package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ViewParceiroController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nomeField;

    @FXML
    private TextField enderecoField;

    @FXML
    private TextField cidadeField;

    @FXML
    private TextField telefoneField;

    @FXML
    private void handleSave() {
        String id = idField.getText();
        String nome = nomeField.getText();
        String endereco = enderecoField.getText();
        String cidade = cidadeField.getText();
        String telefone = telefoneField.getText();

        // Inserir os dados no banco de dados
        String url = "jdbc:mysql://localhost:3306/java";
        String user = "root";
        String password = "";

        String query = "INSERT INTO parceiros (id, nome, endereco, cidade, telefone) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id);
            preparedStatement.setString(2, nome);
            preparedStatement.setString(3, endereco);
            preparedStatement.setString(4, cidade);
            preparedStatement.setString(5, telefone);

            preparedStatement.executeUpdate();

            System.out.println("Parceiro cadastrado com sucesso!");
            clearFields();

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar parceiro: " + e.getMessage());
        }
    }
    
    private void clearFields() {
        idField.clear();
        nomeField.clear();
        enderecoField.clear();
        cidadeField.clear();
        telefoneField.clear();
        
        idField.requestFocus();
    }
}
