package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Role;
import com.eemrezcn.channel.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUnidirectionalTest
{
    @Autowired
    private UserRepository userRepository;

    // Kullanıcı kaydetme ve rolleri ilişkilendirme testi
    @Test
    void saveUser()
    {
        User user = new User();
        user.setFirstName("Emre");
        user.setLastName("Ozcan");
        user.setEmail("emre@gmail.com");
        user.setPassword("123456");

        Role admin = new Role();
        admin.setName("ROLE_ADMIN");

        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        user.getRoles().add(admin);
        user.getRoles().add(customer);
        userRepository.save(user);
    }
    // Kullanıcı güncelleme ve yeni bir rol ekleyerek ilişkilendirme testi
    @Test
    void updateUser(){
        User user = userRepository.findById(1L).get();
        user.setFirstName("ali");
        user.setEmail("ali@gmail.com");

        Role roleUser = new Role();
        roleUser.setName("ROLE_USER");

        user.getRoles().add(roleUser);

        userRepository.save(user);
    }

    // Kullanıcı ve ilişkili rolleri getirme testi
    @Test
    void fetchUser(){
        User user = userRepository.findById(1L).get();
        System.out.println(user.getEmail());
        user.getRoles().forEach((r) -> {
            System.out.println(r.getName());
        });
    }
    // Kullanıcı silme testi
    @Test
    void deleteUser(){
        userRepository.deleteById(1L);
    }
}
