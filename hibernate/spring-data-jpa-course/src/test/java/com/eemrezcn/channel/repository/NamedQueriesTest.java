package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class NamedQueriesTest
{
    @Autowired
    private ProductRepository productRepository;

    // İsme göre fiyat sorgusu (JPQL)
    @Test
    void namedJPQLQuery()
    {
        Product product = productRepository.findByPrice(new BigDecimal(100));

        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // İsme göre sıralanmış ürünleri getirme sorgusu (JPQL)
    @Test
    void namedJPQLQueries()
    {
        List<Product> products= productRepository.findAllOrderByNameDesc();

        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });

        Product product = productRepository.findByPrice(new BigDecimal(200));
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // Açıklamaya göre ürün sorgusu (Native SQL)
    @Test
    void namedNativeSQLQuery()
    {
        Product product = productRepository.findByDescription("Product 1 Description");

        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // Açıklamaya göre sıralanmış ürünleri getirme sorgusu (Native SQL)
    @Test
    void namedNativeSQLQueries()
    {
        Product product = productRepository.findByDescription("Product 1 Description");
        System.out.println(product.getName());
        System.out.println(product.getDescription());

        List<Product> products= productRepository.findAllOrderByNameASC();

        products.forEach(p -> {
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }
}
