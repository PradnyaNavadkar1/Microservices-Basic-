package com.demo.userservice.entities;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="microservices_userservice")
public class User {

    @Id
    @Column(name="UserId")
    private String userId;
    @Column(name="Name")
    private String name;
    @Column(name="Email")
    private String email;
    @Column(name="About")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();


}
