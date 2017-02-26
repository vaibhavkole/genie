package com.business.repository;

import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import com.business.models.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
public interface StatusRepository extends CrudRepository<Status, Integer> {
    Status findByStatusName(String statusName);

}
