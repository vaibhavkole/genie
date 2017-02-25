package com.business.repository;

import com.business.models.Shipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Repository
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {

    Shipment findByMerchantIdAndShipmentRefNumber(Integer merchantId, String shipmentRefNumber);
}
