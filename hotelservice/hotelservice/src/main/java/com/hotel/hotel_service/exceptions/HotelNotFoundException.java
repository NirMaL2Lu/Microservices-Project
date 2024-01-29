package com.hotel.hotel_service.exceptions;

public class HotelNotFoundException extends RuntimeException {

    public HotelNotFoundException(){
        super("Resource not found ");
    }
    public HotelNotFoundException(String message) {
        super(message);
    }
}
