package com.mobicool.e.store.dto;

import org.springframework.http.HttpStatus;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageResponse {

    private String imageName;

    private String message;

    private boolean success;

    private HttpStatus status;
}
