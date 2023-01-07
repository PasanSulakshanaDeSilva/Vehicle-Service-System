package controller;

import db.DBConnection;
import javafx.scene.control.Alert;
import model.Booking;
import model.Customer;
import util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingCrudController {
    public static boolean addBooking(Booking b) throws SQLException, ClassNotFoundException {
        /*PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement("INSERT INTO Booking VALUES (?,?,?,?)");
        preparedStatement.setString(1, b.getBookingId());
        preparedStatement.setDate(2, Date.valueOf(b.getDate()));
        preparedStatement.setTime(3, Time.valueOf(b.getTime()));
        preparedStatement.setString(4, b.getCustomerId());*/

        return CrudUtil.execute("INSERT INTO Booking VALUES (?,?,?,?)", b.getBookingId(), b.getDate(), b.getTime(), b.getCustomerId());


    }

    public ArrayList<Booking> getAllBookings() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Booking");
        ResultSet rst = stm.executeQuery();
        ArrayList<Booking> clients = new ArrayList<>();
        while (rst.next()) {
            clients.add(new Booking(
                    rst.getString(1),
                    rst.getDate(2).toLocalDate(),
                    rst.getTime(3).toLocalTime(),
                    rst.getString(4)
            ));
        }
        return clients;
    }

    /*public boolean DeleteBooking(String text) {
        if (DBConnection.getInstance().getConnection().prepareStatement("DELETE FROM Booking WHERE bookingId='" + id + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }*/
}
