package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
