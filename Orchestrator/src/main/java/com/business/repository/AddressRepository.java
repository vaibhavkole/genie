package com.business.repository;

import com.business.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

}
