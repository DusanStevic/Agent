package com.agent.shop.repository;

import com.agent.shop.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsername(String username);
}
