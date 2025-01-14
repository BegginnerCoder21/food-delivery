package com.api.food_delivery_api.entity;

import com.api.food_delivery_api.enumeration.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
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
    @Column(length = 10)
    private String gender;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(unique = true, nullable = false)
    private String phoneNumber;
    private String address;
    @Column(unique = true, nullable = false)
    private String email;
    private UserType userType;
    private String status;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Device> devices;

}
