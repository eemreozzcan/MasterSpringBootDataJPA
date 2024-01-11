package com.eemrezcn.channel.service.impl;


import com.eemrezcn.channel.entity.Product;
import com.eemrezcn.channel.repository.ProductRepository;
import com.eemrezcn.channel.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link ProductService} arayüzünü uygulayan ve iş mantığıyla ilgili işlemleri gerçekleştiren servis sınıfı.
 */
@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    /**
     * {@code ProductServiceImpl} sınıfı için bağımlılıkları enjekte eden constructor.
     *
     * @param productRepository Ürünlerle ilgili veritabanı işlemlerini gerçekleştiren repository.
     */
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Belirtilen sorgu kelimesine göre ürünleri arar.
     *
     * @param query Arama sorgusu.
     * @return Arama sonucunda bulunan ürün listesi.
     */
    @Override
    public List<Product> searchProducts(String query) {
        // SQL tabanlı arama yapılması için ProductRepository'deki searchProductsSQL metodu kullanılır.
        List<Product> products = productRepository.searchProductsSQL(query);
        return products;
    }

    /**
     * Yeni bir ürün oluşturur ve veritabanına kaydeder.
     *
     * @param product Oluşturulacak ürün.
     * @return Oluşturulan ürün.
     */
    @Override
    public Product createProduct(Product product) {
        // Veritabanına yeni bir ürün eklemek için ProductRepository'deki save metodu kullanılır.
        return productRepository.save(product);
    }
}