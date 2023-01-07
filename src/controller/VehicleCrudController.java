package controller;

import db.DBConnection;
import model.Item;
import model.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleCrudController {
    public static boolean addVehicle(Vehicle v) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Vehicle VALUES (?,?,?,?)");
        preparedStatement.setObject(1, v.getVehicle_Id());
        preparedStatement.setObject(2, v.getVehicle_Name());
        preparedStatement.setObject(3, v.getVehicle_Colour());
        preparedStatement.setObject(4, v.getCustomer_Id());

        return preparedStatement.executeUpdate() > 0;

    }
    public ArrayList<Vehicle> getAllVehicles() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Vehicle");
        ResultSet rst = stm.executeQuery();
        ArrayList<Vehicle> clients = new ArrayList<>();
        while (rst.next()) {
            clients.add(new Vehicle(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            ));
        }
        return clients;
    }
    public static List<String> getCustomerIDs() throws SQLException, ClassNotFoundException {
        ResultSet rst = DBConnection.getInstance().

                getConnection().prepareStatement("SELECT cId FROM Customer").executeQuery();
        List<String> names = new ArrayList<>();
        while (rst.next()) {
            names.add(
                    rst.getString(1)
            );
        }
        return names;
    }
    public boolean DeleteVehicle(String id) throws SQLException, ClassNotFoundException {
        if (DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Vehicle WHERE vehicleId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
