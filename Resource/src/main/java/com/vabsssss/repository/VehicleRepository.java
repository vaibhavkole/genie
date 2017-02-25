package com.vabsssss.repository;

import com.vabsssss.models.Vehicle;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by heena.h on 26/02/17.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
//    TempModel findByTenantName(String tenantName);

}
