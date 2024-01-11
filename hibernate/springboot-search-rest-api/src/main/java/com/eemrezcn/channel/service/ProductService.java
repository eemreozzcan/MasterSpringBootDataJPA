package com.eemrezcn.channel.service;

import com.eemrezcn.channel.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     * Belirtilen sorgu kelimesine göre ürünleri arar.
     *
     * @param query Arama sorgusu.
     * @return Arama sonucunda bulunan ürün listesi.
     */
    List<Product> searchProducts(String query);

    /**
     * Yeni bir ürün oluşturur ve veritabanına kaydeder.
     *
     * @param product Oluşturulacak ürün.
     * @return Oluşturulan ürün.
     */
    Product createProduct(Product product);
}
