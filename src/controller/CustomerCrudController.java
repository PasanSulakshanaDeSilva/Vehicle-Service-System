package controller;

import db.DBConnection;
import model.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrudController {
    public static boolean addCustomer(Customer c) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
        preparedStatement.setObject(1, c.getCustomer_Id());
        preparedStatement.setObject(2, c.getCustomer_Name());
        preparedStatement.setObject(3, c.getCustomer_Address());
        preparedStatement.setObject(4, c.getContact());

        return preparedStatement.executeUpdate() > 0;
    }
    public ArrayList<Customer> getAllCustomers() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Customer");
        ResultSet rst = stm.executeQuery();
        ArrayList<Customer> clients = new ArrayList<>();
        while (rst.next()) {
            clients.add(new Customer(
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
    public boolean DeleteCustomer(String id) throws SQLException, ClassNotFoundException {
        if (DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Customer WHERE cId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

}

