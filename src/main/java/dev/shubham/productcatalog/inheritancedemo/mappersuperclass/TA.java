package dev.shubham.productcatalog.inheritancedemo.mappersuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_ta")
public class TA extends User{
    private Double averageRating;
}
