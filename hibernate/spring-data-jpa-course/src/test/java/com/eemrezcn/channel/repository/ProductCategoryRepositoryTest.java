package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import com.eemrezcn.channel.entity.ProductCategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    // Ürün kategorisi ve bu kategoriye ait ürünlerin kaydedilmesini test eder
    @Test
    void saveProductCategory()
    {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("books");
        productCategory.setCategoryDescription("books description");

        Product product1 = new Product();
        product1.setName("Core Java");
        product1.setPrice(new BigDecimal(1000));
        product1.setImageUrl("image1.png");
        product1.setSku("ABCD");
        product1.setActive(true);
        product1.setCategory(productCategory);
        productCategory.getProducts().add(product1);

        Product product2= new Product();
        product2.setName("Effective Java");
        product2.setPrice(new BigDecimal(2000));
        product2.setImageUrl("image2.png");
        product2.setSku("ABCDE");
        product2.setActive(true);
        product2.setCategory(productCategory);
        productCategory.getProducts().add(product2);

        productCategoryRepository.save(productCategory);
    }

    // Ürün kategorisinin ve bu kategoriye ait ürünlerin getirilmesini test eder
    @Test
    @Transactional
    void fetchProductCategory()
    {
        ProductCategory category = productCategoryRepository.findById(1L).get();
        System.out.println(category.getProducts());
    }
}