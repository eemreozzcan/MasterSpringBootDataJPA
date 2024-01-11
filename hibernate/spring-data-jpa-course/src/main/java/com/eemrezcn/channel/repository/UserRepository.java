package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
