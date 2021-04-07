package com.enp.bibliotheque.benp.persons.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor @AllArgsConstructor @Builder
public class Emprunte {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Column(name = "Date d'emprunte", nullable = false)
    private LocalDateTime dateEmprunte;

    @Column(name = "Date d'echeance", nullable = false)
    private LocalDateTime dateTimeEcheance;

    @Getter @Setter
    private Integer nbreEmprunte;

    @ManyToOne
    @Getter @Setter
    private Agent agent;

    @OneToMany
    @Getter @Setter
    private Book book;

    public LocalDateTime getDateEmprunte() {
        dateEmprunte = LocalDateTime.now();
        return dateEmprunte;
    }

    public LocalDateTime getDateTimeEcheance() {
        dateTimeEcheance = dateEmprunte.plusDays(15);
        return dateTimeEcheance;
    }
}
