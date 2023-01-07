
package model;

import java.sql.Date;
import java.time.LocalDate;

public class Repair {
     private String repairId;
     private String vehicleId;
     private String customerId;
     private LocalDate date;

    public Repair(String repairId, String vehicleId, String date, String customerId) {
    }

    public Repair(String repairId, String vehicleId, String customerId, LocalDate date) {
        this.repairId = repairId;
        this.vehicleId = vehicleId;
        this.customerId = customerId;
        this.date = date;
    }

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Repair{" +
                "repairId='" + repairId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                '}';
    }
}
