package com.makemytrip.makemytrip.services;
import com.makemytrip.makemytrip.models.Users;
import com.makemytrip.makemytrip.models.Users.Booking;
import com.makemytrip.makemytrip.models.Flight;
import com.makemytrip.makemytrip.models.Hotel;
import com.makemytrip.makemytrip.repositories.UserRepository;
import com.makemytrip.makemytrip.repositories.FlightRepository;
import com.makemytrip.makemytrip.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service


public class BookingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private HotelRepository hotelRepository;



        public Booking bookFlight(String userId, String flightId, int seats, double price){
        Optional<Users> usersOptional=userRepository.findById(userId);
        Optional<Flight> flightOptional=flightRepository.findById(flightId);
        if(usersOptional.isPresent() && flightOptional.isPresent()){
            Users user=usersOptional.get();
            Flight flight=flightOptional.get();
            if(flight.getAvailableSeats() >=seats){
                flight.setAvailableSeats(flight.getAvailableSeats()-seats);
                flightRepository.save(flight);

                Booking booking=new Booking();
                booking.setType("Flight");
                booking.setBookingId(flightId);
                booking.setDate(LocalDate.now().toString());
                booking.setQuantity(seats);
                booking.setTotalPrice(price);
                user.getBookings().add(booking);
                userRepository.save(user);
                return booking;
            }
            else {throw new RuntimeException("Not enough seats available");
        }}
        throw new RuntimeException("User or Flight not Found");
    };
    public Booking bookHotel(String userId, String hotelId, int rooms, double price){
        Optional<Users> usersOptional=userRepository.findById(userId);
        Optional<Hotel> hotelOptional=hotelRepository.findById(hotelId);
        if(usersOptional.isPresent() && hotelOptional.isPresent()){
            Users user=usersOptional.get();
            Hotel hotel=hotelOptional.get();
            if(hotel.getAvailableRooms() >=rooms){
                hotel.setAvailableRooms(hotel.getAvailableRooms()-rooms);
                hotelRepository.save(hotel);

                Booking booking=new Booking();
                booking.setType("Hotel");
                booking.setBookingId(hotelId);
                booking.setDate(LocalDate.now().toString());
                booking.setQuantity(rooms);
                booking.setTotalPrice(price);
                user.getBookings().add(booking);
                userRepository.save(user);
                return booking;
            }
            else {throw new RuntimeException("Not enough rooms available");
            }}
        throw new RuntimeException("User or Hotel not Found");
    };


    public Booking cancelBooking(String userId,String bookingId, String type, String reason) {

        // Fetch user
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Find booking inside user's bookings list
        Users.Booking booking = user.getBookings().stream()
                .filter(b -> b.getBookingId().toString().equals(bookingId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        // Update booking details
        booking.setStatus(Users.Booking.BookingStatus.CANCELLED);
        booking.setCancelReason(reason);
        booking.setType(type);
        booking.setCancelledAt(LocalDateTime.now().toString());

        // Save user (not booking!)
        userRepository.save(user);

        return booking;
    }

}