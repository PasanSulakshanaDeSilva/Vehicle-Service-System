package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Booking;
import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class TableBookingFormController {
    public AnchorPane tblBookingForm;
    public TableView tblBooking;
    public TableColumn colBookingId;
    public TableColumn colBookingDate;
    public TableColumn colBookingTime;
    public TableColumn colBkCustomerId;

    public void initialize(){

        colBookingId.setCellValueFactory(new PropertyValueFactory("bookingId"));
        colBookingDate.setCellValueFactory(new PropertyValueFactory("date"));
        colBookingTime.setCellValueFactory(new PropertyValueFactory("time"));
        colBkCustomerId.setCellValueFactory(new PropertyValueFactory("customerId"));

        try {
            setBookings(new BookingCrudController().getAllBookings());


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void setBookings(ArrayList<Booking> bookings) {
        ObservableList<Booking> obList = FXCollections.observableArrayList();
        bookings.forEach(e->{
            obList.add(
                    new Booking(e.getBookingId(),e.getDate(),e.getTime(),e.getCustomerId()));
        });
        tblBooking.setItems(obList);
    }
}
