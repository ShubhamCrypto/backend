package dev.shubham.productcatalog.inheritancedemo.mappersuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_mentor")
public class Mentor extends  User{
    private Double averageRating;
}
