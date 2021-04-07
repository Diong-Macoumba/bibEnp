package com.enp.bibliotheque.benp.persons.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "users")
public class User implements Serializable {

    private static final String PHONE_NUMBER_REGEXP = "^(\\+221|00221)?(77|78|70|76|75)[0-9]{7}$";
    private static final Pattern PHONE_NUMBER_PATTERN = compile(PHONE_NUMBER_REGEXP);

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String firstname;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String lastname;

    @Column(nullable = false, unique = true, length = 25)
    @Size(max = 25)
    @NotBlank
    private String username;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private String password;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    @Transient
    private String repassword;

    @Column(nullable = false, unique = true, length = 25)
    @Size(max = 25)
    @Email(regexp = ".+@.+\\..+")
    @NotBlank
    private String email;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String gender;

    @Column(nullable = false, unique= true, length = 14)
    @Size(min=8, max = 14)
    @javax.validation.constraints.Pattern(regexp = PHONE_NUMBER_REGEXP)
    private String phoneNumber;

    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    Collection<Role> roles = new ArrayList<>();

    public String toString() {
        return new ToStringBuilder(this)
                .append("idENPUser", id)
                .append("prenom", firstname)
                .append("nom", lastname)
                .append("username", username)
                .append("password", password)
                .append("rePassword", repassword)
                .append("email", email)
                .append("active", active)
                .append("telephone", phoneNumber)
                .append("gender", gender)
                .append("enpRoles", roles)
                .toString();
    }
}
