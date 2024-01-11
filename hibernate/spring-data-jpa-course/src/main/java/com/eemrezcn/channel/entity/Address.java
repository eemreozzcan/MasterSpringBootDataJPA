package com.eemrezcn.channel.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    // Bir-tek ilişki belirtir: Bir adres bir siparişe aittir ve bir sipariş bir adrese aittir.
    @OneToOne(cascade = CascadeType.ALL)
    // İki tabloyu birbirine bağlamak için kullanılır. Bu durumda, `addresses` tablosundaki `order_id` sütunu,
    // `orders` tablosundaki `id` sütunu ile ilişkilendirilir.
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
