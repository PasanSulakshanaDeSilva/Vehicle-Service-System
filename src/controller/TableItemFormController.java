package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import model.Item;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableItemFormController {
    public TableColumn colItemId;
    public TableColumn colItemName;
    public TableColumn colItemPrice;
    public TableColumn colItemQty;
    public AnchorPane tblItemForm;
    public TableView tblItems;

    public void initialize(){

        colItemId.setCellValueFactory(new PropertyValueFactory("Item_Id"));
        colItemName.setCellValueFactory(new PropertyValueFactory("Item_Name"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory("Item_Price"));
        colItemQty.setCellValueFactory(new PropertyValueFactory("Item_Qty"));

        try {
            loadAllItem();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadAllItem() throws ClassNotFoundException, SQLException{
        ResultSet result = CrudUtil.execute("SELECT  * FROM vehicle_service.stock");
        ObservableList<Item> obLIst = FXCollections.observableArrayList();
        while (result.next()) {
            obLIst.add(
                    new Item(
                            result.getString("itemId"),
                            result.getString("itemName"),
                            result.getDouble("itemPrice"),
                            result.getInt("itemQty")
                    )
            );
        }
        tblItems.setItems(obLIst);
    }
}

