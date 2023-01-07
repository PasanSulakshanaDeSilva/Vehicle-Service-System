package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class RepairFormController {
    public JFXTextField txtRepairId;
    public JFXComboBox cmbVehicleId;
    public JFXComboBox cmbCustId;
    public JFXDatePicker txtDate;
    public AnchorPane tblRepairForm;
    public TableView tblRepairs;
    public TableColumn colRepairId;
    public TableColumn colRepairDate;
    public TableColumn colRepairVehicleId;
    public TableColumn colRepairCustomerId;

    public void btnAddOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }
}
