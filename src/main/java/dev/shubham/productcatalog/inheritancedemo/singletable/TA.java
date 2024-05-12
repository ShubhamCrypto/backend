package dev.shubham.productcatalog.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "st_ta")
@DiscriminatorValue(value = "3")
public class TA extends User {
    private Double averageRating;
}
