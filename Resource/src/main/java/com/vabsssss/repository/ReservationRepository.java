package com.vabsssss.repository;

import com.vabsssss.models.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by heena.h on 26/02/17.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
//    TempModel findByTenantName(String tenantName);

}
