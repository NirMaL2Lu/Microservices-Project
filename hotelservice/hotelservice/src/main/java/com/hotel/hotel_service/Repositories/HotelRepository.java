package com.hotel.hotel_service.Repositories;

import com.hotel.hotel_service.entites.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,String > {

}
