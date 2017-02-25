package com.genie.transport.repository;

import com.genie.transport.model.Connection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akashdeep.saluja on 25/02/17.
 */
@Repository
public interface IConnectionRepository extends CrudRepository<Connection, Integer> {
}
