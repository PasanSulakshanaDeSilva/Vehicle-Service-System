package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TableRepairFormController {
    public AnchorPane tblRepairForm;
    public TableColumn colRepairVehicleId;
    public TableColumn colRepairId;
    public TableColumn colRepairDate;
    public TableColumn colRepairCustomerId;
    public TableView tblRepairs;

    public void initialize(){

        colRepairId.setCellValueFactory(new PropertyValueFactory("repairId"));
        colRepairDate.setCellValueFactory(new PropertyValueFactory("date"));
        colRepairVehicleId.setCellValueFactory(new PropertyValueFactory("vehicleId"));
        colRepairCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));

        try {
            loadAllRepair();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllRepair() throws ClassNotFoundException, SQLException{
        ResultSet result = CrudUtil.execute("SELECT  * FROM vehicle_service.repair");
        ObservableList<Item> obLIst = FXCollections.observableArrayList();
        while (result.next()) {
            obLIst.add(
                    new Item(
                            result.getString("repairId"),
                            result.getString("date"),
                            result.getDouble("vehicleId"),
                            result.getInt("customerId")
                    )
            );
        }
        tblRepairs.setItems(obLIst);
    }
}
