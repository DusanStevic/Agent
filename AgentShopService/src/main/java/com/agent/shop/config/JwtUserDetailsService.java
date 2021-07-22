package com.agent.shop.config;

import com.agent.shop.model.entity.Agent;
import com.agent.shop.repository.AgentRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {


    private AgentRepository agentRepository;

    public JwtUserDetailsService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    //Method gets the User with given username from database
    // if user with that username doesn't  exists the method will throw exception
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Agent agent = agentRepository.findByUsername(username);
        if (agent == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return agent;
        }
    }

}

