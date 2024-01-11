package com.eemrezcn.channel.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*Lombok kütüphanesinin sağladığı @Getter, @Setter, @NoArgsConstructor, @AllArgsConstructor, @ToString
annotation'ları ile getter, setter, parametresiz constructor, parametreli constructor ve toString()
 metodlarını otomatik olarak oluşturuyoruz.*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity // Bu sınıfın bir JPA (Java Persistence API) varlığı olduğunu belirtir.
//@NamedQuery(
//        name = "Product.findByPrice",
//        query = "SELECT p from Product p where p.price = :price"
//)

@NamedQueries(
        {
                @NamedQuery(
                        name = "Product.findAllOrderByNameDesc",
                        query = "SELECT p from Product p ORDER By p.name DESC"
                ),
                @NamedQuery(
                        name = "Product.findByPrice",
                        query = "SELECT p from Product p where p.price = :price"
                )
        }
)

//@NamedNativeQuery(
//        name = "Product.findByDescription",
//        query = "select * from products p where p.description = :description",
//        resultClass = Product.class
//)

@NamedNativeQueries(
        {
                @NamedNativeQuery(
                        name = "Product.findByDescription",
                        query = "select * from products p where p.description = :description",
                        resultClass = Product.class
                ),
                @NamedNativeQuery(
                        name = "Product.findAllOrderByNameASC",
                        query = "select * from products order by name asc",
                        resultClass = Product.class
                )
        }
)
@Table(
        name = "products",
        schema = "ecommerce",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "sku_unique",
                        columnNames = "stock_keeping_unit"
                )
        }
)
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_generator"
    )

    @SequenceGenerator(
            name = "product_generator",
            sequenceName = "product_sequence_name",
            allocationSize = 1
    )
    private Long id;

    @Column(name = "stock_keeping_unit", nullable = false)
    private String sku;

    @Column(nullable = false)
    private String name;

    private String description;
    private BigDecimal price;
    private boolean active;
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    // Bir-çok ilişki belirtir: Bir ürün bir kategoriye aittir.
    @ManyToOne
    /*İki tabloyu birbirine bağlamak için kullanılır. Bu durumda, `products` tablosundaki `category_id` sütunu,
    `product_categories` tablosundaki `id` sütunu ile ilişkilendirilir.*/

    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private ProductCategory category;
}