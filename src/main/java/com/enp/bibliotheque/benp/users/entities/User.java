package com.enp.bibliotheque.benp.users.entities;

import com.enp.bibliotheque.benp.users.enums.Gender;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.platform.commons.util.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Transient
    private String repassword;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "email", nullable = false)
    public String email;

    @Column(name = "active", nullable = false)
    public Boolean active;

    private String phoneNumber;

    private ZonedDateTime creationDate;

    private ZonedDateTime lastConnectionDate;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn( name = "users_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn( name = "role_id" , referencedColumnName = "id"))
    List<GrantedAuthority> authorities = new ArrayList<>();

    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstname", firstname)
                .append("lastname", lastname)
                .append("username", username)
                .append("gender", gender)
                .append("phoneNumber", phoneNumber)
                .append("active", active)
                .append("creationDate", creationDate)
                .append("lastConnectionDate", lastConnectionDate)
                .append("authorities", authorities)
                .toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
