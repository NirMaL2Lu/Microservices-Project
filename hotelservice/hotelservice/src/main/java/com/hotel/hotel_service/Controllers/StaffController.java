package com.hotel.hotel_service.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping
    public ResponseEntity<List<String >> getAllStaff(){

        List<String> list = Arrays.asList("Ram", "Sita", "Gita", "Mita");
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
