package com.mobicool.e.store.controller;

import com.mobicool.e.store.dto.ApiResponseMessage;
import com.mobicool.e.store.dto.ImageResponse;
import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.dto.UserDto;
import com.mobicool.e.store.helper.ApiConstants;
import com.mobicool.e.store.service.FileService;
import com.mobicool.e.store.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileService fileService;

    @Value("${user.profile.image.path}")
    private String imageUploadPath;

    /**
     *
     * @author Pankaj Gosavi
     * @param userDto
     * @return
     * @ApiNote This API is used to create user
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Request Starting for service layer to create user");
        UserDto userDto1 = userService.createUser(userDto);
        log.info("Request completed for service layer to save the user");
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);

    }

    /**
     * @author Pankaj Gosavi
     * @param userId
     * @param userDto
     * @return
     * @ApiNote This API is used to Update user
     */
    @PutMapping("/{userId}/userId") //url change
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") String userId, @RequestBody UserDto userDto) {
        log.info("Request Starting for service layer to update user");
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        log.info("Request Starting for service layer to update user");
        return new ResponseEntity<>(updatedUserDto, HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @param userId
     * @return
     * @ApiNote This API is used to Delete user
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponseMessage> deleteUser(@PathVariable String userId) {
        log.info("Request Starting for service layer to delete user by userId {}", userId);
        userService.deleteUser(userId);
        ApiResponseMessage message = ApiResponseMessage
                .builder()
                .message("User is deleted Sucessfully!!  ")
                .success(true).
                status(HttpStatus.OK)
                .build();
        log.info("Request completed for service layer to delete user by userId {}", userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @return
     * @ApiNote This API is used to GetAllUser Information
     */
    @GetMapping
    public ResponseEntity<PageableResponse<UserDto>> getAllUsers( //app constants
            @RequestParam(value = "PageNumber", defaultValue = ApiConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "PageSize", defaultValue = ApiConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ApiConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ApiConstants.SORT_DIRECTION, required = false) String sortDir
    ) {
        log.info("Request  for service layer to get All user ");
        return new ResponseEntity<>(userService.getAllUser(pageNumber, pageSize, sortBy, sortDir), HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @param userId
     * @return
     * @ApiNote This API is used to Get Single User by Id
     */
    @GetMapping("/{userId}")

    public ResponseEntity<UserDto> getUserById(@PathVariable String userId) {
        log.info("Request for service layer to get user by userId {}", userId);
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @param email
     * @return
     * @ApiNote This API is used to Get User By Email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUser(@PathVariable String email) {
        log.info("Request  for service layer to get user by email {}", email);
        return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
    }
    /**
     * @author Pankaj Gosavi
     * @param keywords
     * @return
     * @ApiNote This API is used to Search user
     */
    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String keywords) {
        log.info("Request for service layer to seach user by keywords {}", keywords);

        return new ResponseEntity<>(userService.searchUser(keywords), HttpStatus.OK);
    }

    /**
     * @author Pankaj Gosavi
     * @param image
     * @param userId
     * @return
     * @throws IOException
     */
    @PostMapping("/image/{userId}") // userimage appconstnst
    public ResponseEntity<ImageResponse> uploadUserImage(@RequestParam(ApiConstants.USER_IMAGE) MultipartFile image, @PathVariable String userId) throws IOException {
        {
            String imageName = fileService.uploadFile(image, imageUploadPath);
            UserDto user = userService.getUserById(userId);
            user.setImageName(imageName);
            UserDto userDto = userService.updateUser(user, userId);
            ImageResponse imageResponse = ImageResponse.builder().imageName(imageName).success(true).status(HttpStatus.CREATED).build();
            return new ResponseEntity<>(imageResponse, HttpStatus.CREATED);
        }
    }
}