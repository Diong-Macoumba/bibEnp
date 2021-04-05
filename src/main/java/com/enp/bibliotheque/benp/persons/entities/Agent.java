package com.enp.bibliotheque.benp.persons.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.compile;


@Data
@Entity
@Table(name = "AGENT")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agent {
    
    private static final String PHONE_NUMBER_REGEXP = "^(\\+221|00221)?(77|78|70|76|75)[0-9]{7}$";
    private static final Pattern PHONE_NUMBER_PATTERN = compile(PHONE_NUMBER_REGEXP);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String prenom;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String nom;

    @Column(nullable = false, length = 25)
    @Size(max = 25)
    private String grade;

    @Column(nullable = false, unique = true)
    @Size(max = 25)
    private String ccap;

    @Column(nullable = false, unique = true, length = 25)
    @Size(max = 25)
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @Column(nullable = false, unique= true, length = 14)
    @Size(min=8, max = 14)
    @javax.validation.constraints.Pattern(regexp = PHONE_NUMBER_REGEXP)
    private String telephone;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("firstName", prenom)
                .append("lastName", nom)
                .append("grade", grade)
                .append("CCAP", ccap)
                .append("email", email)
                .append("phoneNumber", telephone)
                .toString();

    }

}
