package dev.shubham.productcatalog.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends  BaseModel{
    private String name;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private List<Product> products;
}
