package dev.shubham.productcatalog.inheritancedemo.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name = "tablePerClass_student")
public class Student extends User {
    private Double psp;
    private Double attendance;
}
