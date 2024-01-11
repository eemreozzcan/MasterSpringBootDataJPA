package com.eemrezcn.channel.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    // Bir-çok ilişki belirtir: Bir rolün birden çok kullanıcısı olabilir ve bir kullanıcının birden çok rolü olabilir.
    @ManyToMany(cascade = {
            // CascadeType, kullanıcılarla ilgili rollerde yapılan değişiklikleri yönetir.
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            // FetchType.EAGER, kullanıcı getirildiğinde rolleri hemen yükler.
            fetch = FetchType.EAGER,
            // mappedBy, ilişkinin sahibini belirtir. Bu durumda, "roles" alanı, "users" alanına sahiptir.
            mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
