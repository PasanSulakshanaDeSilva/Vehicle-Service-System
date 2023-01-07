package model;

public class Customer {
    private String customer_Id;
    private String customer_Name;
    private String customer_Address;
    private String contact;

    public Customer() {
    }

    public Customer(String customer_Id, String customer_Name, String customer_Address, String contact) {
        this.customer_Id = customer_Id;
        this.customer_Name = customer_Name;
        this.customer_Address = customer_Address;
        this.contact = contact;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public String getCustomer_Name() {
        return customer_Name;
    }

    public String getCustomer_Address() {
        return customer_Address;
    }

    public String getContact() {
        return contact;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    public void setCustomer_Address(String customer_Address) {
        this.customer_Address = customer_Address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_Id='" + customer_Id + '\'' +
                ", customer_Name='" + customer_Name + '\'' +
                ", customer_Address='" + customer_Address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}