package com.mobicool.e.store.dto;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {

    @NotBlank(message = "Category ID is required")
    private String categoryId;

    @NotBlank(message = "Title is required")
    @Size(min=10,max = 100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min=25,max= 500, message = "Description cannot exceed 500 characters")
    private String description;

    private String coverImage;
}
