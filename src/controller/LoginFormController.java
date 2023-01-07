package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public AnchorPane loginForm;

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if ((txtUserName.getText().equals("Admin"))&&(txtPassword.getText().equals("1234"))){
            Parent root = FXMLLoader.load(this.getClass().getResource("../view/DashBoardForm.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) this.loginForm.getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.setTitle("DashBoard Form");
            primaryStage.centerOnScreen();
        }
        else  {
            Alert alert = new Alert(Alert.AlertType.WARNING,"Invalid entry!");
            Optional<ButtonType> buttonType = alert.showAndWait();
            txtUserName.clear();
            txtPassword.clear();
            txtUserName.requestFocus();

        }


    }

    public void passwordOnAction(ActionEvent actionEvent) throws IOException {
        btnLoginOnAction(actionEvent);
    }
}
