package dev.shubham.productcatalog.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(generator = "uuidgenertor")
    @GenericGenerator(name="uuidgenertor",strategy ="uuid2")
    @Column(name = "id",columnDefinition = "binary(16)",nullable = false,updatable = false)

    private UUID id;
    //https://uuid.ramsey.dev/en/stable/rfc4122/version7.html
}


//@GenericGenerator(name="uuidgenertor",strategy = GenerationType.UUID)// it will make a string => 32 BYTES  USING BITS WE CAN STORE IN 16 BYTES

// @Id
//@GeneratedValue(strategy = GenerationType.UUID)
//private UUID id;

// https://www.baeldung.com/java-hibernate-uuid-primary-key