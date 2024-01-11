package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.awt.print.Pageable;
import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest
{
    @Autowired
    private ProductRepository productRepository;

    // Sayfalama testi
    @Test
    void pagination() {
        int pageNo = 0;
        int pageSize = 5;

        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();


        products.forEach(p -> {
            System.out.println(p);
        });

        int totalPage = page.getTotalPages();

        long totalElements = page.getTotalElements();

        int numberOfElements = page.getNumberOfElements();

        int size = page.getSize();

        boolean isLast = page.isLast();

        boolean isFirst = page.isFirst();

        System.out.println("Total Page: " + totalPage);
        System.out.println("Total Elements: " + totalElements);
        System.out.println("Number Of Elements: " + numberOfElements);
        System.out.println("Size: " + size);
        System.out.println("Is Last: " + isLast);
        System.out.println("Is First: " + isFirst);

    }

    // Sıralama testi
    @Test
    void sorting() {

        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        List<Product> products = productRepository.findAll(sort);

        products.forEach(p -> {
            System.out.println(p);
        });
    }

    // Birden fazla alanı kullanarak sıralama testi
    @Test
    void sortingMultipleFields() {

        String sortBy = "price";
        String sortByDesc = "description";
        String sortDir = "desc";

        Sort sortByName=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Sort sortByDescription = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByDesc).ascending() : Sort.by(sortByDesc).descending();

        Sort groupBySort = sortByName.and(sortByDescription);

        List<Product> products = productRepository.findAll(groupBySort);

        products.forEach(p -> {
            System.out.println(p);
        });

    }
    // Sayfalama ve sıralama işlemlerinin birlikte kullanıldığı test
    @Test
    void paginationAndSortingTogether()
    {
        String sortBy = "price";
        String sortDir = "desc";
        int pageNo = 0;
        int pageSize = 5;

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> page = productRepository.findAll(pageable);

        List<Product> products = page.getContent();

        products.forEach(p -> {
            System.out.println(p);
        });

        int totalPage = page.getTotalPages();

        long totalElements = page.getTotalElements();

        int numberOfElements = page.getNumberOfElements();

        int size = page.getSize();

        boolean isLast = page.isLast();

        boolean isFirst = page.isFirst();

        System.out.println("Total Page: " + totalPage);
        System.out.println("Total Elements: " + totalElements);
        System.out.println("Number Of Elements: " + numberOfElements);
        System.out.println("Size: " + size);
        System.out.println("Is Last: " + isLast);
        System.out.println("Is First: " + isFirst);
    }
}
