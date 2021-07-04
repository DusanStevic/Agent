package com.agent.product.repository;

import com.agent.product.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsername(String username);
}
