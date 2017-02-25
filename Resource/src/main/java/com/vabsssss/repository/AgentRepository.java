package com.vabsssss.repository;

import com.vabsssss.models.Agent;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by heena.h on 26/02/17.
 */
public interface AgentRepository extends CrudRepository<Agent, Integer> {
    Agent findById(Integer id);
}

