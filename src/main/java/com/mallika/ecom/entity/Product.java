package com.mallika.ecom.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @Lob
    private String description;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
//    @OnDelete(action = onDeleteAction.CASCADE)
    @JoinColumn(name = "category_id", nullable = false)
    @JsonIgnore
    private Category category;
}
