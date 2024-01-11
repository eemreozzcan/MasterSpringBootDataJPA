package com.eemrezcn.channel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryName;
    private String categoryDescription;

    //// Bir-çok ilişki belirtir: Bir kategoriye birden çok ürün aittir.
    @OneToMany(cascade = {
            // CascadeType, ürünlerle ilgili kategoriye yapılan değişiklikleri yönetir.
            CascadeType.PERSIST,
            CascadeType.MERGE,
  },        // FetchType.LAZY, kategori getirildiğinde ürünleri yüklemeyi geciktirir.
            fetch = FetchType.LAZY,
            // mappedBy, ilişkinin sahibini belirtir. Bu durumda, "category" alanı, "products" alanına sahiptir.
            mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
