package com.enp.bibliotheque.benp.users.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Role implements GrantedAuthority {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @Override
    @Transient
    public String getAuthority() {
        return role;
    }
}
