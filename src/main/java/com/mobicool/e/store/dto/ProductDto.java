package com.mobicool.e.store.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDto {


    private String productId;

    private String title;


    private String description;

    private Double price;

    private Double discountedPrice;

    private Long quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;
}
