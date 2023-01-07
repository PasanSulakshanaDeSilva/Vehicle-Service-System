package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;
import model.Item;
import model.Vehicle;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddItemFormController {
    public JFXTextField txtItemId;
    public JFXTextField txtItemName;
    public JFXTextField txtItemPrice;
    public JFXTextField txtItemQty;

    public void backButtonOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Item i = new Item(
                txtItemId.getText(),
                txtItemName.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemQty.getText())

        );
        try {
            if (ItemCrudController.addItem(i))
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        Item c = new Item(
                txtItemId.getText(), txtItemName.getText(), Double.parseDouble(txtItemPrice.getText()), Integer.parseInt(txtItemQty.getText())
        );

        try {
            boolean isUpdated = CrudUtil.execute("UPDATE vehicle_service.stock SET itemId=? , itemName=? , itemPrice=? , itemQty=? WHERE itemId=?", c.getItem_Id(), c.getItem_Name(), c.getItem_Price(), c.getItem_Qty(), c.getItem_Id());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new ItemCrudController().DeleteItems(txtItemId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void itemEnterOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT * FROM vehicle_service.stock WHERE itemId=?", txtItemId.getText());
            if (resultset.next()) {
                txtItemId.setText(resultset.getString(1));
                txtItemName.setText(resultset.getString(2));
                txtItemPrice.setText(resultset.getString(3));
                txtItemQty.setText(resultset.getString(4));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Item !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Item search(String ItemId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From vehicle_service.stock WHERE itemId = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, ItemId);
        if (resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public void btnItemSearchOnAction(ActionEvent actionEvent) {
        search();
    }
}
