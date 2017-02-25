package com.vabsssss.repository;

import com.vabsssss.models.PickupRequestResponse;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dheeraj.mittal on 26/02/17.
 */
public interface PickupRequestRepository extends CrudRepository<PickupRequestResponse,Integer> {
    PickupRequestResponse findByShipmentId(Integer shipmentId);
}
