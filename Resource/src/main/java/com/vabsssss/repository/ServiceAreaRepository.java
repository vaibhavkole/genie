package com.vabsssss.repository;

import com.vabsssss.models.ServiceArea;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by heena.h on 26/02/17.
 */
public interface ServiceAreaRepository extends CrudRepository<ServiceArea, Integer> {
    ServiceArea findByType(String pincode);

}
