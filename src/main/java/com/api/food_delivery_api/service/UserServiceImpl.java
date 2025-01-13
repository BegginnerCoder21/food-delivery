package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.UserRequest;
import com.api.food_delivery_api.dto.UserResponse;
import com.api.food_delivery_api.entity.Device;
import com.api.food_delivery_api.entity.User;
import com.api.food_delivery_api.repository.DeviceRepository;
import com.api.food_delivery_api.repository.UserRepository;
import com.api.food_delivery_api.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Transactional
    @Override
    public UserResponse create(UserRequest userRequest) {

        UserUtils.usernameNotEmpty(userRequest.getUsername());
        UserUtils.phoneNumberNotEmpty(userRequest.getPhoneNumber());

        User user = this.modelMapper.map(userRequest, User.class);
        this.userRepository.save(user);

        this.saveDevice(userRequest, user);


        return null;
    }

    public void saveDevice(UserRequest userRequest, User user)
    {
        if(user.getId() != null)
        {
            final UserRequest.DeviceRequest deviceRequest = userRequest.getDeviceRequest();
            Device model = Device.builder()
                    .deviceId(deviceRequest.getDeviceId())
                    .deviceModel(deviceRequest.getDeviceModel())
                    .appVersion(deviceRequest.getAppVersion())
                    .osVersion(deviceRequest.getOsVersion())
                    .isTrusted(deviceRequest.isTrusted())
                    .deviceType(deviceRequest.getDeviceType())
                    .user(deviceRequest.getUser())
                    .build();

            this.deviceRepository.save(model);
            System.out.println("ici " + model);
        }
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse delete(Long id) {
        return null;
    }

    @Override
    public UserResponse findById(Long id) {
        return null;
    }

    @Override
    public List<UserResponse> allUser() {
        return List.of();
    }
}
