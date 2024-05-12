package dev.shubham.productcatalog.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tablePerClass_ta")
public class TA extends User {
    private Double averageRating;
}
