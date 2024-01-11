package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest
{
    @Autowired
    private ProductRepository productRepository;


    // Yeni bir ürün kaydetme işlemini test eder
    @Test
    public void saveMethod()
    {
        Product product = new Product();
        product.setName("Iphone 12");
        product.setDescription("New Iphone 12");
        product.setSku("ABC123");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        Product savedObject = productRepository.save(product);

        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());
    }

    // Varolan bir ürünü güncelleme işlemini test eder
    @Test
    void updateUsingSaveMethod()
    {
        Long id=1L;
        Product product = productRepository.findById(id).get();

        product.setName("updated product 1");
        product.setDescription("updated product 1");

        productRepository.save(product);
    }
    // Id'ye göre bir ürünü getirme işlemini test eder
    @Test
    void findByIdMethod()
    {
        Long id=1L;
        Product product = productRepository.findById(id).get();

        System.out.println(product.toString());
    }

    // Birden fazla ürünü kaydetme işlemini test eder
    @Test
    void saveAllMethod(){
        Product product = new Product();
        product.setName("Product 1");
        product.setDescription("Product 1 Description");
        product.setSku("114ABCD");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product1.png");

        Product product3= new Product();
        product3.setName("Product2");
        product3.setDescription("roduct 2 Description");
        product3.setSku("115ABCDE");
        product3.setPrice(new BigDecimal(200));
        product3.setActive(true);
        product3.setImageUrl("product2.png");

        productRepository.saveAll(List.of(product,product3));
    }

    // Tüm ürünleri getirme işlemini test eder
    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        products.forEach((p)-> System.out.println(p.getName()));
    }

    // Id'ye göre bir ürünü silme işlemini test eder
    @Test
    void deleteByIdMethod(){
        Long id=1L;
        productRepository.deleteById(id);
    }

    // Belirli bir ürünü silme işlemini test eder
    @Test
    void deleteMethod(){

        Long id = 2L;
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    // Birden fazla ürünü silme işlemini test eder
    @Test
    void deleteAllMethod(){
        //productRepository.deleteAll();

        Product product = productRepository.findById(4L).get();
        Product product1 = productRepository.findById(5L).get();

        productRepository.deleteAll(List.of(product,product1));
    }

    // Belirli bir Id'ye sahip ürünün var olup olmadığını kontrol etme işlemini test eder
    @Test
    void existsByIdMethod(){
        Long id = 7L;
        boolean exists = productRepository.existsById(id);
        System.out.println(exists);
    }

    // Toplam ürün sayısını getirme işlemini test eder
    @Test
    void countMethod(){
        Long count = productRepository.count();
        System.out.println(count);
    }
}