package com.demo.userservice.entities;


import lombok.*;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class Hotel {
    private  String id;
    private String name;
    private String location;
    private String about;

}
