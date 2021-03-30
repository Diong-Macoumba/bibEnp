package com.enp.bibliotheque.benp.security.entity;

import com.enp.bibliotheque.benp.users.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token")
@Data @NoArgsConstructor @AllArgsConstructor
public class AuthToken {

    @Id  @GeneratedValue(generator = "UUID")
    @GenericGenerator( name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "last_access_token", nullable = false)
    private LocalDateTime lastAccessToken;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @PrePersist
    public void onPrePersist() {
        lastAccessToken = LocalDateTime.now();
    }
}
