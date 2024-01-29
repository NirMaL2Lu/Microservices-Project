package com.hotel.hotel_service.entites;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    private String id;
    private String name;
    private String location;
    private String about;
}
