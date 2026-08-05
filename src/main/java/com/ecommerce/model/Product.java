package com.ecommerce.model;
import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Column(nullable = false)
    private Double price;

    private Double originalPrice;

    @NotNull(message = "Stock is required")
    @PositiveOrZero(message = "Stock cannot be negative")
    @Column(nullable = false)
    private Integer stock;

    private String imageUrl;

    @Column(length = 2000)
    private String imageUrls;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;

    private String brand;
    
    private String color;

    @Column(length = 2000)
    private String highlights;

    @Column(length = 300)
    private String sizes;

    @Column(length = 2000)
    private String specifications;

    @Column(columnDefinition = "double default 0.0")
    private Double rating = 0.0;

    @Column(columnDefinition = "integer default 0")
    private Integer reviewCount = 0;

    @Column(columnDefinition = "integer default 5")
    private Integer deliveryDays = 5;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active = true;
}