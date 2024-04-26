package dev.shubham.productcatalog.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequestDto {
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
