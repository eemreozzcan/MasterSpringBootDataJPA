package com.eemrezcn.channel.dto;

import com.eemrezcn.channel.entity.Order;
import com.eemrezcn.channel.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
