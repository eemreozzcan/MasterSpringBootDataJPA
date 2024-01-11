package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Address;
import com.eemrezcn.channel.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest
{

    @Autowired
    private OrderRepository orderRepository;

    // Sipariş kaydetme testi
    @Test
    void saveOrderMethod()
    {
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411407");

        order.setBillingAddress(address);

        orderRepository.save(order);

    }

    // Sipariş bilgilerini getirme testi
    @Test
    void getOrderMethod()
    {
        Order order = orderRepository.findById(1L).get();
        System.out.println(order.toString());
    }

    // Sipariş güncelleme testi
    @Test
    void updateOrderMethod()
    {
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");
        order.getBillingAddress().setZipCode("411087");
        orderRepository.save(order);
    }

    // Sipariş silme testi
    @Test
    void deleteOrderMethod()
    {
        Order order = orderRepository.findById(1L).get();
        orderRepository.delete(order);
    }
}
