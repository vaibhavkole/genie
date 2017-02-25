package com.vabsssss.repository;

import com.vabsssss.models.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by heena.h on 26/02/17.
 */
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
    List<Vehicle> findByStatusAndHubId(Integer status, Integer hubId);
    List<Vehicle> findByHubId(Integer hubId);
    Vehicle findById(Integer id);

}
