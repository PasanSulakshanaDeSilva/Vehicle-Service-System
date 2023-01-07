package model;

public class Vehicle {
    private String vehicle_Id;
    private String vehicle_Name;
    private String vehicle_Colour;
    private String customer_Id;

    public Vehicle() {
    }

    public Vehicle(String vehicle_Id, String vehicle_Name, String vehicle_Colour, String customer_Id) {
        this.setVehicle_Id(vehicle_Id);
        this.setVehicle_Name(vehicle_Name);
        this.setVehicle_Colour(vehicle_Colour);
        this.setCustomer_Id(customer_Id);
    }

    public String getVehicle_Id() {
        return vehicle_Id;
    }

    public void setVehicle_Id(String vehicle_Id) {
        this.vehicle_Id = vehicle_Id;
    }

    public String getVehicle_Name() {
        return vehicle_Name;
    }

    public void setVehicle_Name(String vehicle_Name) {
        this.vehicle_Name = vehicle_Name;
    }

    public String getVehicle_Colour() {
        return vehicle_Colour;
    }

    public void setVehicle_Colour(String vehicle_Colour) {
        this.vehicle_Colour = vehicle_Colour;
    }

    public String getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(String customer_Id) {
        this.customer_Id = customer_Id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicle_Id='" + getVehicle_Id() + '\'' +
                ", vehicle_Name='" + getVehicle_Name() + '\'' +
                ", vehicle_Colour='" + getVehicle_Colour() + '\'' +
                ", customer_Id='" + getCustomer_Id() + '\'' +
                '}';
    }
}
