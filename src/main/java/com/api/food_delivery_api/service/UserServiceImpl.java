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
import java.util.Optional;

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

    @Transactional
    @Override
    public UserResponse update(Long id, UserRequest userRequest) {

        Optional<User> user = this.userRepository.findById(id);

        if(user.isEmpty())
        {
            throw new IllegalArgumentException("Utilisation avec ce idientifiant n'existe pas.");
        }

        User userUpdate = user.get();

        userUpdate.setUserType(userRequest.getUserType());
        userUpdate.setEmail(userRequest.getEmail());
        userUpdate.setDateOfBirth(userRequest.getDateOfBirth());
        userUpdate.setUsername(userRequest.getUsername());
        userUpdate.setAddress(userRequest.getAddress());
        userUpdate.setLastname(userRequest.getLastname());
        userUpdate.setGender(userRequest.getGender());
        userUpdate.setFirstname(userRequest.getFirstname());
        userUpdate.setPhoneNumber(userRequest.getPhoneNumber());
        userUpdate.setStatus(userRequest.getStatus());

        this.userRepository.save(userUpdate);

        if(userUpdate.getDevices() != null)
        {
            final UserRequest.DeviceRequest deviceRequest = userRequest.getDeviceRequest();

            if(StringUtils.hasText(deviceRequest.getDeviceId()))
            {
                List<Device> devices = userUpdate.getDevices();

                Device deviceUpdate = devices.stream()
                        .filter(device -> device.getDeviceId()
                        .equalsIgnoreCase(deviceRequest.getDeviceId()))
                        .findAny()
                        .orElse(null);

                if(deviceUpdate != null)
                {

                    deviceUpdate.setOsVersion(deviceRequest.getOsVersion());
                    deviceUpdate.setAppVersion(deviceRequest.getAppVersion());
                    deviceUpdate.setTrusted(deviceRequest.isTrusted());
                    deviceUpdate.setDeviceType(deviceRequest.getDeviceType());
                    deviceUpdate.setDeviceModel(deviceRequest.getDeviceModel());

                    this.deviceRepository.save(deviceUpdate);

                    return UserResponse.builder().build();
                }

                Device creatingDevice = Device.builder()
                        .deviceId(deviceRequest.getDeviceId())
                        .deviceModel(deviceRequest.getDeviceModel())
                        .appVersion(deviceRequest.getAppVersion())
                        .osVersion(deviceRequest.getOsVersion())
                        .isTrusted(deviceRequest.isTrusted())
                        .deviceType(deviceRequest.getDeviceType())
                        .user(userUpdate)
                        .build();

                this.deviceRepository.save(creatingDevice);

                return UserResponse.builder().build();
            }

            throw new IllegalArgumentException("L'id de l'appareil ne doit pas être vide");

        }

        return UserResponse.builder().build();
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
