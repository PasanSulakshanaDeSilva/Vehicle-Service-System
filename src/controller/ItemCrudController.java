package controller;

import db.DBConnection;
import model.Customer;
import model.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCrudController {
    public static boolean addItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Stock VALUES (?,?,?,?)");
        preparedStatement.setObject(1, i.getItem_Id());
        preparedStatement.setObject(2, i.getItem_Name());
        preparedStatement.setObject(3, i.getItem_Price());
        preparedStatement.setObject(4, i.getItem_Qty());

        return preparedStatement.executeUpdate() > 0;
    }
    public ArrayList<Item> getAllItems() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Stock");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> clients = new ArrayList<>();
        while (rst.next()) {
            clients.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3),
                    rst.getInt(4)
            ));
        }
        return clients;
    }

    public static List<String> getItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().

                getConnection().prepareStatement("SELECT itemId FROM Stock").executeQuery();
        List<String> names1= new ArrayList<>();
        while (rst.next()) {
            names1.add(
                    rst.getString(1)
            );
        }
        return names1;
    }

    public boolean DeleteItems(String id) throws SQLException, ClassNotFoundException {
        if (DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Stock WHERE itemId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }


}
