package com.mobicool.e.store.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String productId;
    @Column(length=10000)
    private String title;


    private String description;

    private Double price;

    private Double discountedPrice;

    private Long quantity;

    private Date addedDate;

    private boolean live;

    private boolean stock;

    private String productImageName;


}
