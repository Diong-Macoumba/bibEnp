package com.enp.bibliotheque.benp.persons.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {
    private String prenom;
    private String nom;
    private String grade;
    private String ccap;
    private String email;
    private String telephone;
}
