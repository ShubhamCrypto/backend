package dev.shubham.productcatalog.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "JoinedTable_student")
@PrimaryKeyJoinColumn(name = "user_id")
public class Student extends User {
    private Double psp;
    private Double attendance;
}
