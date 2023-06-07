package com.mobicool.e.store.controller;

import com.mobicool.e.store.dto.ApiResponse;
import com.mobicool.e.store.dto.UserDto;
import com.mobicool.e.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to create user
     * @param userDto
     * @return
     */

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Starting request to create new User");
        UserDto userDto1 = userService.createUser(userDto);
        log.info("Completed request to create new User");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);

    }

    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to update user with userId
     * @param userDto
     * @param userId
     * @return
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable("userId") String userId, @RequestBody UserDto userDto) {
        log.info("Starting request to update new User :" +userId);
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        log.info("Completed request to update new User  :" +userId);
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);

    }
    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to delete user
     * @param userId
     * @return
     */

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId) {
        log.info("Starting request to delete User with userId: " +userId);
        userService.deleteUser(userId);
        ApiResponse message = ApiResponse
                .builder()
                .message("User is deleted Sucessfully!!  ")
                .success(true).
                status(HttpStatus.OK)
                .build();
        log.info("Complete request to delete User with userId: " +userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to get all users
     * @return
     */

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Starting request to get all users");
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to get users by userId
     * @return
     */

    //get single
    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") String userId) {
        log.info("Starting request to get User by userId: " +userId);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to get all users by email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        log.info("Starting request to get User with email: " +email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);

    }

    /**
     * @author Pankaj Gosavi
     * @apiNote This Api is to search user by keywords
     * @return
     */
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
        log.info("Starting request search user with keywords :"+keywords);
        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);

    }

}