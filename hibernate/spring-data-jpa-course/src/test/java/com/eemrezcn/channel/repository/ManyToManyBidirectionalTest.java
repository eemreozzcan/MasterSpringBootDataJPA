package com.eemrezcn.channel.repository;

import com.eemrezcn.channel.entity.Role;
import com.eemrezcn.channel.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManyToManyBidirectionalTest {

    @Autowired
    private RoleRepository roleRepository;

    // Rol kaydetme ve kullanıcı rol ilişkisi oluşturma testi
    @Test
    void saveRole(){
        User user = new User();
        user.setFirstName("osman");
        user.setLastName("oz");
        user.setEmail("osman@gmail.com");
        user.setPassword("123456");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        roleAdmin.getUsers().add(user);
        roleAdmin.getUsers().add(admin);

        user.getRoles().add(roleAdmin);
        admin.getRoles().add(roleAdmin);

        roleRepository.save(roleAdmin);
    }
    // Rolleri ve bu rollerin ilişkili olduğu kullanıcıları getirme testi
    @Test
    void fetchRole(){
        List<Role> roles = roleRepository.findAll();
        roles.forEach((r) ->{
            System.out.println(r.getName());
            r.getUsers().forEach((u) ->{
                System.out.println(u.getFirstName());
            });
        });
    }
}
