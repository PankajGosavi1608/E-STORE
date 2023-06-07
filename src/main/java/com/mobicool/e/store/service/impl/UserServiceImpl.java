package com.mobicool.e.store.service.impl;
import com.mobicool.e.store.dto.UserDto;
import com.mobicool.e.store.entity.User;
import com.mobicool.e.store.repository.UserRepository;
import com.mobicool.e.store.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {

        String UserID = UUID.randomUUID().toString();
        userDto.setUserId(UserID);
        //dto->entity
        User user = dtoToEntity(userDto);
        User saveUser = userRepository.save(user);
        //entity-> dto
        UserDto newDto = entityToDto(saveUser);
        return newDto;
    }

    public UserDto updateUser(UserDto userDto, String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not updated"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        user.setGender(userDto.getGender());
        user.setImageName(userDto.getImageName());
        User updatedUser = userRepository.save(user);
        UserDto updatedDto = entityToDto(updatedUser);
        return updatedDto;
    }

    //Delete User
    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not found"));

        this.userRepository.delete(user);
    }

    //GetUserByID
    public UserDto getUserById(String userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not Found"));
        return entityToDto(user);

    }

    //GetAll USer
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).
                orElseThrow(() -> new RuntimeException("User not Found with given email id and password"));
        return entityToDto(user);
    }

    public List<UserDto> searchUser(String keyword) {
        List<User> users = userRepository.findByNameContaining(keyword);

        List<UserDto> dtoList = users.stream().map(user -> entityToDto(user)).collect(Collectors.toList());
        return dtoList;
    }

    private UserDto entityToDto(User savedUser) {
//        UserDto userDto=UserDto.builder()
//                .id(savedUser.getId())
//                .name(savedUser.getName())
//                .email(savedUser.getEmail())
//                .password(savedUser.getPassword())
//                .about(savedUser.getAbout())
//                .gender(savedUser.getGender())
//                .imageName(savedUser.getImageName()).build();
//                return userDto;


        return modelMapper.map(savedUser, UserDto.class);

    }

    private User dtoToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);


    }


}