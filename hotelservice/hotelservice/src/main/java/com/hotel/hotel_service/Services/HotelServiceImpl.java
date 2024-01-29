package com.hotel.hotel_service.Services;

import com.hotel.hotel_service.Repositories.HotelRepository;
import com.hotel.hotel_service.entites.Hotel;
import com.hotel.hotel_service.exceptions.HotelNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotel() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getSingleHotel(String id) {
        return hotelRepository
                .findById(id).orElseThrow(() -> new HotelNotFoundException("Hotel with given id not found !! "+id));
    }
}
