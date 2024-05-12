package dev.shubham.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    //           P ; C
    //=>L TO R : 1 : 1
    //=>R TO L : M : 1
    //=> ANS   : M : 1
    @ManyToOne
    private Category category;
    private double price;
    //double has precision issues
}
