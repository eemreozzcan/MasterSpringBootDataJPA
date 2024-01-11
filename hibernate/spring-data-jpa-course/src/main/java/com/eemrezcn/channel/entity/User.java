package com.eemrezcn.channel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_email",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    // Bir-çok ilişki belirtir: Bir kullanıcının birden çok role sahip olabilir ve bir rol birden çok kullanıcıya ait olabilir.
    @ManyToMany(
            // FetchType.EAGER, roller getirildiğinde ilişkili kullanıcıları hemen yükler.
            fetch = FetchType.EAGER,
            // CascadeType.ALL, kullanıcılarda veya rollerde yapılan değişiklikleri yönetir.
            cascade = CascadeType.ALL
    )
// JoinTable, ilişki tablosunun adını ve bu tablonun her iki tarafındaki sütunları belirtir.
    @JoinTable(
            name = "users_roles", // İlişki tablosunun adı
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id" // Kullanıcıların sütunu
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id" // Rollerin sütunu
            )
    )
    private Set<Role> roles = new HashSet<>();
}
