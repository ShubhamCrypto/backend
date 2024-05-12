package dev.shubham.productcatalog.inheritancedemo.joinedtable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity(name = "JoinedTable_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private Double averageRating;
}
