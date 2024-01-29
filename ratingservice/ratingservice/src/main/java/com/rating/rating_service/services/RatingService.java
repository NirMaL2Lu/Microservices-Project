package com.rating.rating_service.services;

import com.rating.rating_service.entites.Rating;

import java.util.List;

public interface RatingService {

    // create rating
    Rating createRating(Rating rating);

    //    get all rating
    List<Rating> getAllRatings();

    // getting all rating by userId
    List<Rating> getRatingsByUserId(String userId);
    // getting all rating by hotel id

    List<Rating> getRatingByHotelId(String hotelId);
}
