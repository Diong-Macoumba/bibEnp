package com.enp.bibliotheque.benp.persons.controllers;

import com.enp.bibliotheque.benp.persons.entities.Agent;
import com.enp.bibliotheque.benp.persons.repositories.AgentRepository;
import com.enp.bibliotheque.benp.persons.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/agents")
public class AgentController {

    @Autowired
    private AgentRepository agentRepository;
    
    @Autowired
    private AgentService agentService;

    @PostMapping(value = "/create")
    public Agent saveAgent(@RequestBody Agent agent) {
        return agentService.saveAgent(agent);
    }

    @GetMapping
    public Page<Agent> getAllAgents(Pageable pageable) {
        return agentService.findAllAgents(pageable);
    }

    @GetMapping("/{id}")
    public Agent getAgentById(@PathVariable Long id) {
        return agentService.findAgentById(id);
    }

    @PutMapping("/update")
    public Agent updateAgent(@RequestParam Long id, @RequestBody Agent agent) {
        return agentService.updateAgent(id, agent);
    }

    @GetMapping(params = "email")
    public Agent getAgentByEmail(@RequestParam String email) {
        return agentService.findAgentByEmail(email);
    }

    @GetMapping(params = "ccap")
    public Agent getAgentByCcap(@RequestParam String ccap) {
        return agentService.findAgentByCcap(ccap);
    }

}
