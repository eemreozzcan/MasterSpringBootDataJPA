package com.eemrezcn.channel.service.impl;


import com.eemrezcn.channel.dto.OrderRequest;
import com.eemrezcn.channel.dto.OrderResponse;
import com.eemrezcn.channel.entity.Order;
import com.eemrezcn.channel.entity.Payment;
import com.eemrezcn.channel.exception.PaymentException;
import com.eemrezcn.channel.repository.OrderRepository;
import com.eemrezcn.channel.repository.PaymentRepository;
import com.eemrezcn.channel.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    /**
     * Siparişinizi verir.
     *
     * @param orderRequest Sipariş ve ödeme detaylarını içeren sipariş isteği.
     * @return Siparişin durumunu gösteren OrderResponse.
     */
    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {

        Order order = orderRequest.getOrder();
        order.setStatus("INPROGRESS");
        order.setOrderTackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if(!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTackingNumber(order.getOrderTackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;
    }
}
