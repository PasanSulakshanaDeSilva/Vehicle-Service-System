package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Booking;
import model.Customer;
import model.Vehicle;
import util.CrudUtil;

import java.sql.SQLException;
import java.time.LocalDate;

public class PlaceBookingFormController {
    public JFXTextField txtBkId;
    public JFXDatePicker txtDate;
    public JFXTimePicker txtTime;
    public JFXComboBox cmbCustId;

    public void initialize() {
        setCustomerIds();
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Booking b = new Booking(
                txtBkId.getText(),
                txtDate.getValue(),
                txtTime.getValue(),
                (String) cmbCustId.getValue()

        );
        System.out.println(b.getCustomerId());
        try {
            if (BookingCrudController.addBooking(b))
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
        Booking b = new Booking(
                txtBkId.getText(), txtDate.getValue(), txtTime.getValue(),
                (String) cmbCustId.getValue()
        );

        try {
            boolean isUpdated = CrudUtil.execute("UPDATE Booking SET bookingId=? , date=? , time=? , customerId=? WHERE bookingId=?", b.getBookingId(), b.getDate(), b.getTime(), b.getCustomerId());
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        } catch (SQLException | ClassNotFoundException e) {

        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
       /* if (new BookingCrudController().DeleteBooking(txtBkId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }*/

    }

    public void setCustomerIds() {
        try {

            ObservableList<String> cIdObList = FXCollections.observableArrayList(
                    CustomerCrudController.getCustomerIDs()
            );
            cmbCustId.setItems(cIdObList);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void bookingSearchOnAction(ActionEvent actionEvent) {
    }
}
