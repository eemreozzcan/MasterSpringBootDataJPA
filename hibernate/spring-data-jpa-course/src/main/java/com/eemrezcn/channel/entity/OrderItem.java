package com.eemrezcn.channel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String imageUrl;
    private BigDecimal price;
    private int quantity;

    // Bir-tek ilişki belirtir: Bir sipariş öğesi bir ürüne aittir.
    @OneToOne
    /*İki tabloyu birbirine bağlamak için kullanılır. Bu durumda, `order_items` tablosundaki `product_id` sütunu,
    `products` tablosundaki `id` sütunu ile ilişkilendirilir.*/
    @JoinColumn(name = "product_id")
    private Product product;
    /*Bir-çok ilişki belirtir: Bir sipariş öğesi bir siparişe aittir, ancak bir sipariş öğesi birden çok siparişe ait olabilir.
    FetchType.LAZY kullanılarak, sipariş öğesi getirildiğinde ilişkili siparişin yüklenmesi geciktirilir.*/
    @ManyToOne(fetch = FetchType.LAZY)
    /*İki tabloyu birbirine bağlamak için kullanılır. Bu durumda, `order_items` tablosundaki `order_id` sütunu,
     `orders` tablosundaki `id` sütunu ile ilişkilendirilir.*/
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
