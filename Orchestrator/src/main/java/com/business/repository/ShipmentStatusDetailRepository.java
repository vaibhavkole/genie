package com.business.repository;

import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Repository
public interface ShipmentStatusDetailRepository extends CrudRepository<ShipmentStatusDetail, Integer> {
    List<ShipmentStatusDetail> findByShipment(Shipment shipment);
    List<ShipmentStatusDetail> findByShipmentId(Integer shipmentId);
}
