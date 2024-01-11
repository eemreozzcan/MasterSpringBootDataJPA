package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Adı veya açıklaması belirtilen kelimeyi içeren ürünleri JPQL sorgusu ile arar.
     *
     * @param query Aranacak kelime.
     * @return Arama sonucunda bulunan ürün listesi.
     */
    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')")
    List<Product> searchProducts(String query);

    /**
     * Adı veya açıklaması belirtilen kelimeyi içeren ürünleri native SQL sorgusu ile arar.
     *
     * @param query Aranacak kelime.
     * @return Arama sonucunda bulunan ürün listesi.
     */
    @Query(value = "SELECT * FROM products p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')" +
            "Or p.description LIKE CONCAT('%', :query, '%')", nativeQuery = true)
    List<Product> searchProductsSQL(String query);
}
