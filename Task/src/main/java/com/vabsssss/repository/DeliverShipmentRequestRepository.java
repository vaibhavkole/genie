package com.vabsssss.repository;

import com.vabsssss.models.DeliverShipmentRequest;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dheeraj.mittal on 26/02/17.
 */
public interface DeliverShipmentRequestRepository extends CrudRepository<DeliverShipmentRequest,Integer> {

    DeliverShipmentRequest findByShipmentId(Integer shipmentId);
}
