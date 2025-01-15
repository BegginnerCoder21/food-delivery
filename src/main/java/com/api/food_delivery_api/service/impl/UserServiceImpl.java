package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.UserRequest;
import com.api.food_delivery_api.dto.UserResponse;
import com.api.food_delivery_api.entity.Device;
import com.api.food_delivery_api.entity.User;
import com.api.food_delivery_api.repository.DeviceRepository;
import com.api.food_delivery_api.repository.UserRepository;
import com.api.food_delivery_api.service.UserService;
import com.api.food_delivery_api.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

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
        user.setCreatedAt(new Date());

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
                    .user(user)
                    .build();

            System.out.println("here " + user);
            this.deviceRepository.save(model);

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
        userUpdate.setUpdatedAt(new Date());

        this.userRepository.save(userUpdate);

        UserResponse userResponse = this.modelMapper.map(userUpdate, UserResponse.class);

        /*
                UserResponse userResponse = UserResponse.builder()
                .email(userUpdate.getEmail())
                .userType(userUpdate.getUserType())
                .id(userUpdate.getId())
                .status(userUpdate.getStatus())
                .address(userUpdate.getAddress())
                .dateOfBirth(userUpdate.getDateOfBirth())
                .firstname(userUpdate.getFirstname())
                .username(userUpdate.getUsername())
                .lastname(userUpdate.getLastname())
                .phoneNumber(userUpdate.getPhoneNumber())
                .build();

         */

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

                    userResponse.setDeviceResponse(List.of(UserResponse.DeviceResponse.builder()
                            .id(deviceUpdate.getId())
                            .appVersion(deviceUpdate.getAppVersion())
                            .deviceId(deviceUpdate.getDeviceId())
                            .deviceModel(deviceUpdate.getDeviceModel())
                            .isTrusted(deviceUpdate.isTrusted())
                            .osVersion(deviceUpdate.getOsVersion())
                            .deviceType(deviceUpdate.getDeviceType())
                            .build()));

                    return userResponse;
                }

                Device creatingDevice = this.modelMapper.map(deviceRequest, Device.class);
                creatingDevice.setUser(userUpdate);

                /*
                Device creatingDevice = Device.builder()
                        .deviceId(deviceRequest.getDeviceId())
                        .deviceModel(deviceRequest.getDeviceModel())
                        .appVersion(deviceRequest.getAppVersion())
                        .osVersion(deviceRequest.getOsVersion())
                        .isTrusted(deviceRequest.isTrusted())
                        .deviceType(deviceRequest.getDeviceType())
                        .user(userUpdate)
                        .build();

                 */

                this.deviceRepository.save(creatingDevice);

                userResponse.setDeviceResponse(List.of(UserResponse.DeviceResponse.builder()
                        .appVersion(creatingDevice.getAppVersion())
                        .deviceId(creatingDevice.getDeviceId())
                        .deviceModel(creatingDevice.getDeviceModel())
                        .isTrusted(creatingDevice.isTrusted())
                        .osVersion(creatingDevice.getOsVersion())
                        .deviceType(creatingDevice.getDeviceType())
                        .build()));

                return userResponse;
            }

            throw new IllegalArgumentException("L'id de l'appareil ne doit pas être vide");

        }

        return userResponse;
    }

    @Override
    public ResponseEntity delete(Long id) {

        boolean userExist = this.userRepository.existsById(id);
        if(!userExist){
            throw new IllegalArgumentException("Utilisateur que vous voulez supprimer n'existe pas !");
        }

        this.userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public UserResponse findById(Long id)
    {
        Optional<User> user = this.userRepository.findById(id);

        if (user.isEmpty())
        {
            throw new IllegalArgumentException("Aucun utilisateur trouvé !");
        }

        User foundUser = user.get();

        List<UserResponse.DeviceResponse> deviceResponsesList = foundUser.getDevices()
                .stream()
                .map(device -> this.modelMapper.map(device, UserResponse.DeviceResponse.class))
                .toList();

        UserResponse userResponse = this.modelMapper.map(foundUser, UserResponse.class);

        userResponse.setDeviceResponse(deviceResponsesList);

        return userResponse;
    }

    @Override
    public List<UserResponse> allUser() {

        List<User> users = this.userRepository.findAll();

        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user: users)
        {

            UserResponse userResponse = this.modelMapper.map(user, UserResponse.class);

            List<UserResponse.DeviceResponse> deviceResponsesList = user.getDevices()
                    .stream()
                    .map(device -> this.modelMapper.map(device, UserResponse.DeviceResponse.class))
                    .toList();

            userResponse.setDeviceResponse(deviceResponsesList);

            userResponseList.add(userResponse);

            /*

            userResponse.getDeviceResponse().addAll(user.getDevices().stream().map(device -> this.modelMapper.map(device, UserResponse.DeviceResponse.class)).toList());

            List<UserResponse.DeviceResponse> deviceResponsesList = user.getDevices()
                    .stream()
                    .map(device -> this.modelMapper.map(device, UserResponse.DeviceResponse.class))
                    .toList();

            UserResponse userResponse = UserResponse.builder()
                    .id(user.getId())
                    .userType(user.getUserType())
                    .firstname(user.getFirstname())
                    .address(user.getAddress())
                    .username(user.getUsername())
                    .lastname(user.getLastname())
                    .email(user.getEmail())
                    .status(user.getStatus())
                    .dateOfBirth(user.getDateOfBirth())
                    .phoneNumber(user.getPhoneNumber())
                    .deviceResponse(deviceResponsesList)
                    .build();

             */

        }

        return userResponseList;
    }
}
