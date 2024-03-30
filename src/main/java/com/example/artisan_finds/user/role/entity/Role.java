package com.example.artisan_finds.user.role.entity;

import com.example.artisan_finds.user.entity.User;
import com.example.artisan_finds.user.role.Permission;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    private Integer id;
    private String name;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
