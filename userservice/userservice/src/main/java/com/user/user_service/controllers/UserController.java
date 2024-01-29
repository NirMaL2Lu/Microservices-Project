package com.user.user_service.controllers;

import com.user.user_service.entites.User;
import com.user.user_service.services.UserService;
import com.user.user_service.services.UserServiceImpl;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    //create user
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saveUser = this.userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }

    int retryCount = 1;

    // get single users
    @GetMapping("/{userId}")
//    @CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "rateLimiter",fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
        logger.info("Get single user handler : UserController");
        logger.info("retry count {} ",retryCount);

        User getSingleUser = this.userService.getUser(userId);

        return ResponseEntity.ok(getSingleUser);
    }

    //writing fall back method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        logger.info("Fallback is executed due to service is down : {}", ex.getMessage());
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("dummyName")
                .about("dummy user is created because service is down")
                .userId("dummy123").build();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //get all users
    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {

        List<User> allUser = this.userService.getAllUser();
        System.out.println("All users : " + allUser);

        return ResponseEntity.ok(allUser);
    }

}
