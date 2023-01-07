package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {
    private String bookingId;
    private LocalDate date;
    private LocalTime time;
    private String customerId;

    public Booking() {
    }

    public Booking(String bookingId, LocalDate date, LocalTime time, String customerId) {
        this.bookingId = bookingId;
        this.date = date;
        this.time = time;
        this.customerId = customerId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
