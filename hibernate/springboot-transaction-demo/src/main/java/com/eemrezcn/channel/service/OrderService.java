package com.eemrezcn.channel.service;


import com.eemrezcn.channel.dto.OrderRequest;
import com.eemrezcn.channel.dto.OrderResponse;

public interface OrderService {

    //description: placeOrder
    //param: [orderRequest]
    //return: com.eemrezcn.channel.dto.OrderResponse
    //date: 2021/7/26 10:58
    OrderResponse placeOrder(OrderRequest orderRequest);
}
