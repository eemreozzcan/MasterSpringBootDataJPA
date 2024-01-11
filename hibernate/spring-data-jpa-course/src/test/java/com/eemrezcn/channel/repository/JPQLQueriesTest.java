package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPQLQueriesTest
{

    @Autowired
    private ProductRepository productRepository;

    // JPQL ile isme veya açıklamaya göre ürün arama testi (index parametreli)
    @Test
    void findByNameOrDescriptionJPQLIndexParam()
    {
        Product product = productRepository.findByNameOrDescriptionJPQLIndexParam("Product 2", "Product 2 Description");

        System.out.println(product.getId());
        System.out.println(product.getName());
    }
    // JPQL ile isme veya açıklamaya göre ürün arama testi (named parametreli)
    @Test
    void findByNameOrDescriptionJPQLNamedParamMethod()
    {
        Product product = productRepository.findByNameOrDescriptionJPQLNamedParam("Product 1", "Product 1 Description");

        System.out.println(product.getId());
        System.out.println(product.getName());
    }
}
