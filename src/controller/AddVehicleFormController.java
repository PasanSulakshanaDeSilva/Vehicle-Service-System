package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;
import model.Vehicle;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddVehicleFormController {
    public JFXComboBox<String> cmbCustomerId;
    public AnchorPane tblVehicleForm;
    public JFXTextField txtVehicleId;
    public JFXTextField txtVehicleName;
    public JFXTextField txtVehicleColour;
    public JFXButton btnAdd;

    public void initialize(){
        setCustomerIds();
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Vehicle v = new Vehicle(
                txtVehicleId.getText(),
                txtVehicleName.getText(),
                txtVehicleColour.getText(),
                cmbCustomerId.getValue()

        );
        if (VehicleCrudController.addVehicle(v))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
        else
            new Alert(Alert.AlertType.WARNING, "Try Again..").show();

    }

    public void btnUpdateVehicleOnAction(ActionEvent actionEvent) {
        Vehicle c = new Vehicle(
                txtVehicleId.getText(),txtVehicleName.getText(), txtVehicleColour.getText(),
                cmbCustomerId.getValue()
        );

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Vehicle SET vehicleId=? , vehicleName=? , vehicleColour=? , customerId=? WHERE vehicleId=?",c.getVehicle_Id(),c.getVehicle_Name(),c.getVehicle_Colour(),c.getCustomer_Id(),c.getVehicle_Id());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }
    }


    public void btnDeleteVehicleOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new VehicleCrudController().DeleteVehicle(txtVehicleId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public  void  setCustomerIds() {
        try {

            ObservableList<String> cIdObList = FXCollections.observableArrayList(
                    CustomerCrudController.getCustomerIDs()
            );
            cmbCustomerId.setItems(cIdObList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void vehicleIdSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT * FROM vehicle_service.Vehicle WHERE vehicleId=?", txtVehicleId.getText());
            if (resultset.next()) {
                txtVehicleId.setText(resultset.getString(1));
                txtVehicleName.setText(resultset.getString(2));
                txtVehicleColour.setText(resultset.getString(3));
            } else {
              //  new Alert(Alert.AlertType.WARNING, "Empty Vehicle !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void txtVehicleIdOnKeyReleased(KeyEvent keyEvent) {
        validate();
        if (keyEvent.getCode()== KeyCode.ENTER) {
            Object response=validate();

            if (response instanceof TextField){
                TextField textField=(TextField) response;
                textField.requestFocus();
            }else if(response instanceof Boolean){
                System.out.println("Valid");
            }
        }
    }

    private Object validate(){
        Pattern vehicleIdPattern = Pattern.compile("^[0-9-A-z]{6,12}$");
        Pattern vehicleNamePatten = Pattern.compile("^[A-z ]{3,20}$");
        Pattern vehicleColourPattern = Pattern.compile("^[A-z0-9 ,/]{3,20}$");


        if(!(vehicleIdPattern.matcher(txtVehicleId.getText()).matches())){
            addError(txtVehicleId);
            return txtVehicleId;
        }else{
            removeError(txtVehicleId);
            if((!vehicleNamePatten.matcher(txtVehicleName.getText()).matches())){
                addError(txtVehicleName);
                return txtVehicleName;
            }else{
                removeError(txtVehicleName);
                if(!(vehicleColourPattern.matcher(txtVehicleColour.getText()).matches())){
                    addError(txtVehicleColour);
                    return txtVehicleColour;
                }else{
                    removeError(txtVehicleColour);

                }
            }
        }
        return true;
    }

    private void removeError(JFXTextField textField) {
        textField.setStyle("-fx-border-color: lightblue");
        btnAdd.setDisable(false);
    }

    private void addError(JFXTextField textField) {
        textField.setStyle("-fx-border-color: orange");
        btnAdd.setDisable(true);
    }
}
