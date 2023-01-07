package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Customer;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddCustomerFormController {
    public JFXTextField txtCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtCustAddress;
    public JFXTextField txtCustContact;
    public JFXButton btnAdd;

    public static Customer search(String CustomerID) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * From vehicle_service.customer WHERE cId = ? ";
        ResultSet resultSet = CrudUtil.execute(sql, CustomerID);
        if (resultSet.next()) {
            return new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        Customer c = new Customer(
                txtCustId.getText(),
                txtCustName.getText(),
                txtCustAddress.getText(),
                txtCustContact.getText()

        );
        try {
            if (CustomerCrudController.addCustomer(c))
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
        Customer c = new Customer(
                txtCustId.getText(),txtCustName.getText(), txtCustAddress.getText(),
                txtCustContact.getText()
        );

        try{
            boolean isUpdated = CrudUtil.execute("UPDATE Customer SET cId=? , cName=? , cAddress=? , contact=? WHERE cId=?",c.getCustomer_Id(),c.getCustomer_Name(),c.getCustomer_Address(),c.getContact(),c.getCustomer_Id());
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated!").show();
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again!").show();
            }


        }catch (SQLException | ClassNotFoundException e){

        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerCrudController().DeleteCustomer(txtCustId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    public void custEnterOnAction(ActionEvent actionEvent) {
        search();
    }

    private void search() {
        try {
            ResultSet resultset = CrudUtil.execute("SELECT * FROM vehicle_service.customer WHERE cId=?", txtCustId.getText());
            if (resultset.next()) {
                txtCustId.setText(resultset.getString(1));
                txtCustName.setText(resultset.getString(2));
                txtCustAddress.setText(resultset.getString(3));
                txtCustContact.setText(resultset.getString(4));
            } else {
               // new Alert(Alert.AlertType.WARNING, "Empty Customer !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        validate();
        if (keyEvent.getCode()== KeyCode.ENTER) {
            Object response=validate();

            if (response instanceof TextField){
                TextField textField=(TextField) response;
                textField.requestFocus();
            }else if(response instanceof Boolean){
                System.out.println("Valid");
            }
        }
    }

    private Object validate(){
        Pattern idPattern = Pattern.compile("^[0-9]{8,12}$");
        Pattern namePatten = Pattern.compile("^[A-z ]{3,15}$");
        Pattern addressPattern = Pattern.compile("^[A-z0-9 ,/]{4,20}$");
        Pattern contactPattern = Pattern.compile("^[0-9]{10,10}$");

        if(!(idPattern.matcher(txtCustId.getText()).matches())){
            addError(txtCustId);
            return txtCustId;
        }else{
            removeError(txtCustId);
            if((!namePatten.matcher(txtCustName.getText()).matches())){
                addError(txtCustName);
                return txtCustName;
            }else{
                removeError(txtCustName);
                if(!(addressPattern.matcher(txtCustAddress.getText()).matches())){
                    addError(txtCustAddress);
                    return txtCustAddress;
                }else{
                    removeError(txtCustAddress);
                    if(!(contactPattern.matcher(txtCustContact.getText()).matches())){
                        addError(txtCustContact);
                        return txtCustContact;
                    }else{
                        removeError(txtCustContact);
                    }
                }
            }
        }
        return true;
    }

    private void removeError(JFXTextField textField) {
        textField.setStyle("-fx-border-color: lightblue");
        btnAdd.setDisable(false);
    }

    private void addError(JFXTextField textField) {
        textField.setStyle("-fx-border-color: red");
        btnAdd.setDisable(true);
    }

}
