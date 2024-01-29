package com.hotel.hotel_service.Services;

import com.hotel.hotel_service.entites.Hotel;

import java.util.List;

public interface HotelService {
    //create hotel
    Hotel createHotel(Hotel hotel);

    // get all hotel
    List<Hotel> getAllHotel();

    // get single hotel
    Hotel getSingleHotel(String id);
}
