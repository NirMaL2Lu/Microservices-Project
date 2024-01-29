package com.rating.rating_service.controllers;

import com.rating.rating_service.entites.Rating;
import com.rating.rating_service.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {

    @Autowired
    private RatingService ratingService;

    // create rating controller
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        this.ratingService.createRating(rating);

        ResponseEntity<Rating> response = ResponseEntity.status(HttpStatus.CREATED).body(rating);
        System.out.println("Ratings Response : "+response);

        return response;
    }

    // get all ratings

    @GetMapping("/getRatings")
    public ResponseEntity<List<Rating>> getRatings(){
        List<Rating> allRatings = ratingService.getAllRatings();
        ResponseEntity<List<Rating>> response = ResponseEntity.ok(allRatings);
        return response;
    }

    // get rating by hotel
    @PreAuthorize("hasAuthority('SCOPE_internal') ||  hasAuthority('Admin')")
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId){
        List<Rating> allRatingsByHotelId = ratingService.getRatingByHotelId(hotelId);
        ResponseEntity<List<Rating>> response = ResponseEntity.ok(allRatingsByHotelId);
        return response;
    }
    // get rating by user

    @PreAuthorize("hasAuthority('SCOPE_internal') ||  hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId){
        List<Rating> allRatingsByUserId = ratingService.getRatingsByUserId(userId);
        ResponseEntity<List<Rating>> response = ResponseEntity.ok(allRatingsByUserId);
        return response;
    }

}
