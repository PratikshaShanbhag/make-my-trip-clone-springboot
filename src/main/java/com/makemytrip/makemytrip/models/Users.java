package com.makemytrip.makemytrip.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;
@Document(collection = "users")

public class Users {
    @Id
    private String _id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNumber;
    private List<Booking> bookings=new ArrayList<>();
    public String getId() {
        return _id;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getPassword(){
        return password;
    }
    public String getEmail(){
        return email;
    }

    public String getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setRole(String role) {
        this.role = role;
    }
public List<Booking> getBookings(){return bookings;}
    public void setBookings(List<Booking> bookings){this.bookings=bookings;}
   // @Entity
public static class Booking{
    private String type;
    private String bookingId;
    private String date;
    private int quantity;
    private double totalPrice;
        //@Enumerated(EnumType.String)
        private BookingStatus status; // e.g., CONFIRMED, CANCELLED
        private String cancelReason;
        private String cancelledAt;

       public void setCancelReason(String cancelReason) {
           this.cancelReason=cancelReason;
       }
       public String getCancelReason(){return cancelReason;}


       // Example CancellationRequest DTO
        //public class CancellationRequest {
            //private String reason;
            // Getters and setters
           //public String getReason(){return reason;}
           // public void setReason(String reason) {
           // this.reason=reason;}
        //}

        // Example BookingStatus Enum
        public enum BookingStatus {
            CONFIRMED, CANCELLED, PENDING
        }

//Getters and Setters
    public String getType(){return type;}
    public void setType(String type){this.type=type;}
    public String getBookingId(){return bookingId;}
    public void setBookingId(String bookingId){this.bookingId=bookingId;}
    public String getDate(){return date;}
    public void setDate(String date){this.date=date;}

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

        public BookingStatus getStatus() {
            return status;
        }

        public void setStatus(BookingStatus status) {
            this.status = status;
        }

        public String getCancelledAt() {
            return cancelledAt;
        }

        public void setCancelledAt(String cancelledAt) {
            this.cancelledAt = cancelledAt;
        }
    }
}