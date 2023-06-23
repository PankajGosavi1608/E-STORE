package com.mobicool.e.store.dto;

import com.mobicool.e.store.validate.ImageNameValid;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class UserDto {

    private String userId;

    @Size(min = 3, max = 15, message = "invalid name")
    private String name;

    @NotBlank(message = "Email is required")
   // @Email(message = "Invalid email format")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", message = "Invalid email format")
    private String email;

    @ImageNameValid(message = "Image name not valid ..")
    private String imageName;

    @NotBlank(message = "Write something about yourself")
    @Size(min = 25, max = 250)
    private String about;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]+$", message = "Invalid password format")
    private String password;

    @Size(min = 4, max = 6, message = "Invalid Gender")
    private String gender;

    @ImageNameValid
    private String image;

}

//Password Note
/*At least one letter (uppercase or lowercase) is required ((?=.*[A-Za-z]))
At least one digit is required ((?=.*\\d))
Special characters are allowed: @$!%*#?&
Only alphanumeric characters and the allowed special characters are allowed ([A-Za-z\\d@$!%*#?&]+)*/