package dev.shubham.productcatalog.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private Category category;
    private double price;
    //double has precision issues
}
