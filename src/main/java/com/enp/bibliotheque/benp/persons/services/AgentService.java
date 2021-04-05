package com.enp.bibliotheque.benp.persons.services;

import com.enp.bibliotheque.benp.exceptions.NotFoundException;
import com.enp.bibliotheque.benp.persons.entities.Agent;
import com.enp.bibliotheque.benp.persons.repositories.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    public Agent saveAgent(Agent agent) {
        Optional<Agent> email = agentRepository.findByEmail(agent.getEmail());
        if (email.isPresent()) {
            throw new IllegalArgumentException("email is already exists");
        }
        Optional<Agent> telephone = agentRepository.findByTelephone(agent.getTelephone());
        if (telephone.isPresent()) {
            throw new IllegalArgumentException("phoneNumber is already exists");
        }
        Optional<Agent> ccap = agentRepository.findByCcap(agent.getCcap());
        if (ccap.isPresent()) {
            throw new IllegalArgumentException("ccap is already exists");
        }
        return agentRepository.save(agent);
    }

    public Page<Agent> findAllAgents(Pageable pageable) {
        return agentRepository.findAll(pageable);
    }

     public Agent findAgentById(Long id) {
       return agentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No agent with id "+id));
     }

     public Agent updateAgent(Long id, Agent agent) {
        Agent agentToUpdate = agentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No agent with id "+id));
         agentToUpdate.setPrenom(agent.getPrenom());
         agentToUpdate.setNom(agent.getNom());
         agentToUpdate.setGrade(agent.getGrade());
         agentToUpdate.setCcap(agent.getCcap());
         agentToUpdate.setEmail(agent.getEmail());
         agentToUpdate.setTelephone(agent.getTelephone());
        return agentRepository.save(agentToUpdate);
     }

     public Agent findAgentByCcap(String ccap) {
        return agentRepository.findByCcap(ccap)
                .orElseThrow(() -> new NotFoundException("No agent with CCAP "+ccap));
     }

    public Agent findAgentByEmail(String email) {
        return agentRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("No agent with email "+email));
    }
}
