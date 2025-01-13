package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String phoneNumber;
    private String address;
    private String email;
    private UserType userType;
    private String status;

}
