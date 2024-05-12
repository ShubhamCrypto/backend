package dev.shubham.productcatalog.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "st_student")
@DiscriminatorValue(value = "2")
public class Student extends User {
    private Double psp;
    private Double attendance;
}
