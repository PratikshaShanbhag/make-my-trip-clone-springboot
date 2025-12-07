package com.makemytrip.makemytrip.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.makemytrip.makemytrip.models.Users;
import com.makemytrip.makemytrip.services.BookingService;
import com.makemytrip.makemytrip.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@RequestMapping("/bookings")


public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping ("/user/{userId}")
    public ResponseEntity<List<Users.Booking>> getuserbookings(@PathVariable String userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user.getBookings());
    }

    @PostMapping("/cancel")
    public Users.Booking cancelBooking(
            @RequestParam String userId,
            @RequestParam String bookingId,
            @RequestParam String type,
            @RequestParam String cancelReason) {
        return bookingService.cancelBooking(userId,bookingId, type, cancelReason);

    }


    @PostMapping("/flight")
    public Users.Booking bookFlight(@RequestParam String userId, @RequestParam String flightId,@RequestParam int seats, @RequestParam double price){
        return bookingService.bookFlight(userId,flightId,seats,price);
    }

    @PostMapping("/hotel")
    public Users.Booking bookHotel(@RequestParam String userId, @RequestParam String hotelId,@RequestParam int rooms, @RequestParam double price) {
        return bookingService.bookHotel(userId, hotelId, rooms, price);
    }

}