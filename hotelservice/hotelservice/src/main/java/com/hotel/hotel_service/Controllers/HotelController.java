package com.hotel.hotel_service.Controllers;

import com.hotel.hotel_service.Services.HotelService;
import com.hotel.hotel_service.entites.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create hotel

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel saveHotel = this.hotelService.createHotel(hotel);

        System.out.println("Hotels are : " + saveHotel);

        return ResponseEntity.status(HttpStatus.CREATED).body(saveHotel);
    }

    //get single hotel

    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId){
        Hotel singleHotel = this.hotelService.getSingleHotel(hotelId);
        return ResponseEntity.ok(singleHotel);

    }

    //get all users

    @PreAuthorize("hasAuthority('SCOPE_internal') ||  hasAuthority('Admin')")
    @GetMapping("/allHotel")
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allHotel = this.hotelService.getAllHotel();
        return ResponseEntity.ok(allHotel);
    }
}
