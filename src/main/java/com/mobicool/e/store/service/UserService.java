package com.mobicool.e.store.service;

import com.mobicool.e.store.dto.PageableResponse;
import com.mobicool.e.store.dto.UserDto;
import com.mobicool.e.store.entity.User;

import java.util.List;


public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,String userId);
    void deleteUser(String userId);
    UserDto getUserById(String userId);
    UserDto getUserByEmail(String email);
    PageableResponse<UserDto> getAllUser(int pageNumber, int pageSize, String sortBy, String sortDir);
    List<UserDto> searchUser(String keywords);
}

