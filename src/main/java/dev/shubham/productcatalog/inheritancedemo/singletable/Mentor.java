package dev.shubham.productcatalog.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "st_mentor")
@DiscriminatorValue(value="1")
public class Mentor extends User {
    private Double averageRating;
}
