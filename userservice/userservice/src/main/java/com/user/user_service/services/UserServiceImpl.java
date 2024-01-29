package com.user.user_service.services;

import com.user.user_service.entites.Hotel;
import com.user.user_service.entites.Rating;
import com.user.user_service.exceptions.UserNotFoundException;
import com.user.user_service.external.services.HotelService;
import com.user.user_service.repositories.UserRepository;
import com.user.user_service.entites.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserid = UUID.randomUUID().toString();
        user.setUserId(randomUserid);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        //implement rating service all using rest template
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help of user repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with given id is not found on server : " + userId));
        //fetch ratings of the above user from rating service
        // http://localhost:8083/ratings/users/b7bdb1f6-80ed-46da-a853-4adf2ad3f63c

        Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{}", ratingsOfUser);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();

        //api call to hotel service  to get the hotel
        List<Rating> ratingList = ratings.stream().map(rating -> {

//            http://localhost:8082/hotels/9909f8d3-9d57-4be2-9dd6-2909e2032a6b
             // api call to hotel service to get the hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//
//            Hotel hotel = forEntity.getBody();

            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("response status code : {}"+forEntity.getStatusCode());
            //see the hotel rating
            rating.setHotel(hotel);
            // return the rating

            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingList);
        System.out.println("User : " + user);
        return user;
    }


}
