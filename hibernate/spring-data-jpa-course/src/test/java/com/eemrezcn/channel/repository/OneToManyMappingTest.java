package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Address;
import com.eemrezcn.channel.entity.Order;
import com.eemrezcn.channel.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // Sipariş kaydetme testi
    @Test
    void saveOrderMethod() {
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("In Progress");

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");
        ;

        order.setBillingAddress(address);
        orderRepository.save(order);


    }
    // Sipariş bilgilerini getirme testi
    @Test
    void fetchOrderMethod() {
        Order order = orderRepository.findById(1L).get();
        System.out.println(order.getStatus());
        System.out.println(order.toString());
        for(OrderItem item: order.getOrderItems()){
            System.out.println(item.getProduct().getName());
        }
    }

    // Sipariş silme testi
    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }

}
