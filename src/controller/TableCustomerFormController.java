package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableCustomerFormController {
    public AnchorPane tblCustomerForm;
    public TableView tblCustomers;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public AnchorPane tblBookingForm;
    public TableView tblBooking;
    public TableColumn colBookingId;
    public TableColumn colBookingDate;
    public TableColumn colBookingTime;
    public TableColumn colBkCustomerId;

    public void initialize(){

        colId.setCellValueFactory(new PropertyValueFactory("customer_Id"));
        colName.setCellValueFactory(new PropertyValueFactory("customer_Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("customer_Address"));
        colContact.setCellValueFactory(new PropertyValueFactory("contact"));

        try {
            setCustomers(new CustomerCrudController().getAllCustomers());


        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void loadAllCustomers() throws ClassNotFoundException, SQLException {
        ResultSet result = CrudUtil.execute("SELECT * FROM Customer");
        ObservableList<Customer> obList = FXCollections.observableArrayList();

        while (result.next()){
            obList.add(
                    new Customer(
                            result.getString("cId"),
                            result.getString("cName"),
                            result.getString("cAddress"),
                            result.getString("contact")
                    )
            );

        }
        tblCustomers.setItems(obList);

    }
    private void setCustomers(ArrayList< Customer > customers) {
        ObservableList<Customer> obList = FXCollections.observableArrayList();
        customers.forEach(e->{
            obList.add(
                    new Customer(e.getCustomer_Id(),e.getCustomer_Name(),e.getCustomer_Address(),e.getContact()));
        });
        tblCustomers.setItems(obList);
    }
}

