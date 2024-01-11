package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NativeSQLQueriesTest
{
    @Autowired
    private ProductRepository productRepository;

    // İsim veya açıklamaya göre ürün sorgusu (SQL, index parametreli)
    @Test
    void findByNameOrDescriptionSQLIndexParamMethod()
    {
        Product product = productRepository.findByNameOrDescriptionSQLIndexParam("Product 2", "Product 2 Description");


        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // İsim veya açıklamaya göre ürün sorgusu (SQL, named parametreli)
    @Test
    void findByNameOrDescriptionSQLNamedParamMethod()
    {
        Product product = productRepository.findByNameOrDescriptionSQLNamedParam("Product 1", "Product 1 Description");

        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }
}
