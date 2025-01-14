package com.api.food_delivery_api.controller;

import com.api.food_delivery_api.dto.UserRequest;
import com.api.food_delivery_api.dto.UserResponse;
import com.api.food_delivery_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food-delivery/")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register-user")
    public ResponseEntity create(@RequestBody UserRequest userRequest)
    {
        try {

            this.userService.create(userRequest);

            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e.getMessage());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/update-user/{id}")
    public UserResponse update(@PathVariable Long id,@RequestBody UserRequest userRequest)
    {
        return this.userService.update(id, userRequest);
    }

    @GetMapping("/find-user/{id}")
    public UserResponse findUser(@PathVariable Long id)
    {
        try {

            return this.userService.findById(id);

        } catch (IllegalArgumentException e) {

            throw new RuntimeException(e.getMessage());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

    }

    @GetMapping("/")
    public List<UserResponse> allUsers()
    {
        return this.userService.allUser();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id)
    {
        return this.userService.delete(id);
    }
}
