package com.mobicool.e.store.helper;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean status;
    private boolean success;
}