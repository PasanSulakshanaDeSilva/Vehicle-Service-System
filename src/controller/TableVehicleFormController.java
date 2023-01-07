package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Item;
import model.Vehicle;

import java.sql.SQLException;
import java.util.ArrayList;

public class TableVehicleFormController {
    public AnchorPane tblVehicleForm;
    public TableColumn colVehicleId;
    public TableColumn colCustomerId;
    public TableColumn colVehicleName;
    public TableColumn colVehicleColour;
    public TableView tblVehicles;


    public void initialize(){

        colVehicleId.setCellValueFactory(new PropertyValueFactory("Vehicle_Id"));
        colVehicleName.setCellValueFactory(new PropertyValueFactory("Vehicle_Name"));
        colVehicleColour.setCellValueFactory(new PropertyValueFactory("Vehicle_Colour"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory("Customer_Id"));

        try {
            setVehicles(new VehicleCrudController().getAllVehicles());


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void setVehicles(ArrayList<Vehicle> vehicles) {
        ObservableList<Vehicle> obList = FXCollections.observableArrayList();
        vehicles.forEach(e->{
            obList.add(
                    new Vehicle(e.getVehicle_Id(),e.getVehicle_Name(),e.getVehicle_Colour(),e.getCustomer_Id()));
        });
        tblVehicles.setItems(obList);
    }
}
