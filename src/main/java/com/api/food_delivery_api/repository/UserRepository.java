package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
