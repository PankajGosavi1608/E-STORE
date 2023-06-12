package com.mobicool.e.store.dto;

import com.mobicool.e.store.entity.CustomFields;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomDto extends CustomFields {
    private String isActive;

    private String createdBy; // validation needed @notnull

    private LocalDateTime createdOn;

    private String modifiedBy; // validation needed

    private LocalDateTime modifiedOn;

}