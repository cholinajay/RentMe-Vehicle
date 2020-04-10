package com.rentme.controller;

import com.rentme.models.BookingDetails;
import com.rentme.models.Vehicle;
import com.rentme.services.VehicleBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class BookingController {

    @Autowired
    VehicleBookingService vehicleBookingService;

    @GetMapping("/get/bookings")
    public ResponseEntity getBookings(){
        return ResponseEntity.ok(vehicleBookingService.getAllBookings());
    }

    @GetMapping("/get/bookings/{customer}")
    public ResponseEntity getBookingsOfCustomer(@PathVariable final String customerId){
        return ResponseEntity.ok(vehicleBookingService.getBookingsOfCustomer(customerId));
    }

    @GetMapping("/get/bookings/{bookingId}")
    public ResponseEntity getBookingById(@PathVariable final String bookingId){
        return ResponseEntity.ok(vehicleBookingService.getBookingById(bookingId));
    }

    @PostMapping("/add/booking")
    public ResponseEntity addBookingInfo(@Valid @RequestBody final BookingDetails bookingDetails,
                                         @RequestParam final String customerId,
                                         @RequestParam final String vehicleId){
        return ResponseEntity
                .accepted()
                .body(vehicleBookingService.addNewBooking(bookingDetails, customerId, vehicleId));
    }

    @PutMapping("/update/booking/{bookingId}")
    public ResponseEntity updateBookingInfo(@PathVariable String bookingId,
                                            @RequestParam Optional<LocalDateTime> toTime,
                                            @RequestParam Optional<Vehicle> vehicle){
        return ResponseEntity
                .accepted()
                .body(vehicleBookingService.updateBooking(bookingId,toTime, vehicle));
    }

    @DeleteMapping("/delete/booking/{bookingId}")
    public ResponseEntity deleteBooking(@PathVariable String bookingId){
        return ResponseEntity.accepted().body(vehicleBookingService.deleteBooking(bookingId));
    }
}
