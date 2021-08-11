package com.agent.report.repository;

import com.agent.report.model.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByUsername(String username);
}
