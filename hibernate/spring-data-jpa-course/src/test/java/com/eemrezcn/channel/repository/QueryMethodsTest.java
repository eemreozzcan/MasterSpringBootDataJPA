package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest
{

    @Autowired
    private ProductRepository productRepository;

    // İsmi "Product 2" olan bir ürünü bulma işlemini test eder
    @Test
    void findByNameMethod()
    {
       Product product = productRepository.findByName("Product 2");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // Belirli bir Id'ye sahip bir ürünü bulma işlemini test eder
    @Test
    void findByIdMethod()
    {
        Product product = productRepository.findById(6L).get();

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // İsim veya açıklaması "Product 2" olan ürünleri bulma işlemini test eder
    @Test
    void findByNameOrDescriptionMethod()
    {
        List<Product> products = productRepository.findByNameOrDescription("Product 2", "Product 2 Description");


        products.forEach(p -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // İsmi "Product 2" olan ve açıklaması "Product 2 Description" olan ürünleri bulma işlemini test eder
    @Test
    void findByNameAndDescriptionMethod()
    {
        List<Product> products = productRepository.findByNameAndDescription("Product 2", "Product 2 Description");


        products.forEach(p -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // İsmi "Product 2" olan ürünleri bulma işlemini test eder (Distinct kullanılarak)
    @Test
    void findDistinctByNameMethod()
    {
        Product product = productRepository.findDistinctByName("Product 2");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    // Fiyati 200'den büyük olan ürünleri bulma işlemini test eder
    @Test
    void findByPriceGreaterThanMethod()
    {
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(200));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // Fiyati 300'den küçük olan ürünleri bulma işlemini test eder
    @Test
    void findByPriceLessThanMethod()
    {
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(300));

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // İsmi "Product 2" içeren ürünleri bulma işlemini test eder
    @Test
    void findByNameContainingMethod()
    {
        List<Product> products = productRepository.findByNameContaining("Product 2");

        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // İsmi "Product 2" ile başlayan ürünleri bulma işlemini test eder
    @Test
    void findByNameLikeMethod(){

        List<Product> products = productRepository.findByNameLike("Product 2");
        products.forEach((p) -> {
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }

    // Fiyatı 100 ile 300 arasındaki ürünleri bulma işlemini test eder
    @Test
    void findByPriceBetweenMethod(){
        List<Product> products = productRepository.findByPriceBetween(
                new BigDecimal(100), new BigDecimal(300)
        );

        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });

    }

    // Belirli bir tarih aralığı içinde oluşturulmuş ürünleri bulma işlemini test eder.
    @Test
    void findByDateCreatedBetweenMethod(){

        // start date
        LocalDateTime startDate = LocalDateTime.of(2022,02,13,17,48,33);
        // end date
        LocalDateTime endDate = LocalDateTime.of(2022,02,13,18,15,21);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    //Belirli bir isim listesine sahip ürünleri bulma işlemini test eder.
    @Test
    void findByNameInMethod(){

        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 2", "product 3"));
        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    //İsim sırasına göre artan şekilde ilk 2 ürünü bulma işlemini test eder.
    @Test
    void findFirst2ByOrderByNameAscMethod(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
    // Fiyat sırasına göre azalan şekilde ilk 2 ürünü bulma işlemini test eder.
    @Test
    void findTop2ByOrderByPriceDescMethod(){
        List<Product> products = productRepository.findTop2ByOrderByPriceDesc();
        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
        });
    }
}
