package dev.shubham.productcatalog.inheritancedemo.mappersuperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "ms_student")
public class Student extends User{
    private Double psp;
    private Double attendance;
}
