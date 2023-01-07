package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardFormController {
    public Pane pane;
    public AnchorPane dbdForm;
    public Pane paneTwo;
    public Label lblDate;
    public Label lblTime;

    public void initialize() throws IOException {
        RunningTime();
        btnDashBoardForm();
        //customerAdd();
    }

    public void btnDashBoardForm() throws IOException {
        URL resource = getClass().getResource("../view/MainForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        paneTwo.getChildren().clear();
        pane.getChildren().add(load);
        //paneTwo.getChildren().add(load);

    }

    private void RunningTime() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }
    public void customerAdd() throws IOException {
        URL resource = getClass().getResource("../view/AddCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
        loadTableCustomer();
    }


    public void btnCustomerForm(ActionEvent actionEvent) throws IOException {
        customerAdd();
    }

    public void loadTableCustomer() throws IOException {
        URL resource = getClass().getResource("../view/TableCustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);
    }

    public void btnVehicleForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
        loadTableVehicle();
    }

    public void loadTableVehicle() throws IOException {
        URL resource = getClass().getResource("../view/TableVehicleForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);
    }

    public void btnBookingForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PlaceBookingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
        loadTableBooking();
    }

    public void loadTableBooking() throws IOException {
        URL resource = getClass().getResource("../view/TableBookingForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);
    }

    public void btnItemForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/AddItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
        loadTableItem();
    }

    public void btnOrderForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        paneTwo.getChildren().clear();
        pane.getChildren().add(load);


    }
   /* public void loadTableOrder() throws IOException {
        URL resource = getClass().getResource("../view/PlaceOrderForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);
    }*/

    public void loadTableItem() throws IOException {
        URL resource = getClass().getResource("../view/TableItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);
    }

    public void btnRepairForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/RepairForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
        loadTableRepair();

    }
    public void loadTableRepair() throws IOException {
        URL resource = getClass().getResource("../view/TableRepairForm.fxml");
        Parent load = FXMLLoader.load(resource);
        paneTwo.getChildren().clear();
        paneTwo.getChildren().add(load);

    }

    public void btnPaymentForm(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/PaymentForm.fxml");
        Parent load = FXMLLoader.load(resource);
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }


    public void btnLogout(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("../view/LoginForm.fxml"));
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) this.dbdForm.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("LoginForm");
        primaryStage.centerOnScreen();
    }

    /*public void loadAllCustomersOnAction(ActionEvent actionEvent) throws IOException {
        setUi("loadAllCustomerForm");
    }

    /*private void setUi(String URI) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("../views/" + URI + ".fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle(URI);
        stage.show();
    }*/
}
