package com.mobicool.e.store.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;

    private String name;

    private String email;

    private String imageName;

    private String about;

    private String password;

    private String gender;

}
