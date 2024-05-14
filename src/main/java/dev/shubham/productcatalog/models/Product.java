package dev.shubham.productcatalog.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    //           P ; C
    //=>L TO R : 1 : 1
    //=>R TO L : M : 1
    //=> ANS   : M : 1
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="category_id")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE  })
    private Price price;
    //double has precision issues
}

//pid title desc image category_id
