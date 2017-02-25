package com.business.repository;

import com.business.models.ShipmentStatusDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Repository
public interface ShipmentStatusDetailRepository extends CrudRepository<ShipmentStatusDetail, Integer> {
}
