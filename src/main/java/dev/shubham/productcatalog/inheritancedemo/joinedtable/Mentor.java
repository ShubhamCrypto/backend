package dev.shubham.productcatalog.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "JoinedTable_mentor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Mentor extends User {
    private Double averageRating;
}
