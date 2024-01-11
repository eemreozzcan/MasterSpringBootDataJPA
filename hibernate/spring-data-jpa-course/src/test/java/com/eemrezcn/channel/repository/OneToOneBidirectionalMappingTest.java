package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Address;
import com.eemrezcn.channel.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;

    // Adres kaydetme testi
    @Test
    void saveAddressMethod(){

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
        address.setZipCode("411047");

        order.setBillingAddress(address);
        address.setOrder(order);

        addressRepository.save(address);
    }

    // Adres güncelleme testi
    @Test
    void updateAddressMethod(){
        Address address = addressRepository.findById(1L).get();
        address.setZipCode("411048");

        address.getOrder().setStatus("DELIVERED");

        addressRepository.save(address);
    }

    // Adres bilgisini getirme testi
    @Test
    void fetchAddressMethod(){
        Address address = addressRepository.findById(1L).get();
    }

    // Adres silme testi
    @Test
    void deleteAddressMethod(){
        addressRepository.deleteById(1L);
    }
}
