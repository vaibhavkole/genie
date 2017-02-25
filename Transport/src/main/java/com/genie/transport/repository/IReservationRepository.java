package com.genie.transport.repository;

import com.genie.transport.model.Connection;
import com.genie.transport.model.Reservation;
import java.sql.Date;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by akashdeep.saluja on 26/02/17.
 */
@Repository
public interface IReservationRepository extends CrudRepository<Reservation, Integer>{

    Reservation findByDateAndConnection(Date date, Connection connection);

}
