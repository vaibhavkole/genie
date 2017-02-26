package com.vabsssss.cont;

import com.vabsssss.models.*;
import com.vabsssss.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by heena.h on 25/02/17.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    AgentRepository agentRepository;

    @Autowired
    HubRepository hubRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ServiceAreaRepository serviceAreaRepository;

    @Autowired
    VehicleRepository vehicleRepository;

    /*@Autowired
    BookmarkRestController(BookmarkRepository bookmarkRepository,
                           AccountRepository accountRepository) {
        this.bookmarkRepository = bookmarkRepository;
        this.accountRepository = accountRepository;
    }*/



    @RequestMapping(method = RequestMethod.GET, value = "/getHubIdForPincode/{pincode}")
    @ResponseBody
    public ResponseEntity<ServiceArea> getHubIdForPincode(@PathVariable String pincode) {
        ServiceArea response = serviceAreaRepository.findByType(pincode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/reservation/{hub_id}/{weight}/{date}")
    public ResponseEntity<?> reservation(@PathVariable Integer hub_id, @PathVariable Double weight, @PathVariable String date) throws ParseException {
        // For the given hub_id fetch available resources.
        List<Vehicle> vehicles = vehicleRepository.findByHubId(hub_id);
        Reservation reservation = new Reservation();
        Integer vehicle_id = getOptimalVehicleId(vehicles,weight);

        reservation.setVehicleId(vehicle_id);
        reservation.setHubId(hub_id);
        reservation.setReservationCapacity(weight);
        reservation.setReservationDate(stringToDate(date));
        reservation.setVehicleId(vehicles.get(0).getId());
        return new ResponseEntity<>(reservationRepository.save(reservation), HttpStatus.CREATED);

    }

    // Update Agent status
    @RequestMapping(method = RequestMethod.POST, value = "/updateAgentStatus/{agent_id}/{status}")
    public ResponseEntity<?> updateAgentStatus(@PathVariable Integer agent_id, @PathVariable Integer status) {
        Agent agent = agentRepository.findById(agent_id);
        agent.setStatus(status);
        return new ResponseEntity<>(agentRepository.save(agent), HttpStatus.OK);
    }

    // Update vehicle status
    @RequestMapping(method = RequestMethod.POST, value = "/updateVehicleStatus/{vehicle_id}/{status}")
    public ResponseEntity<?> updateVehicleStatus(@PathVariable Integer vehicle_id, @PathVariable Integer status) {
        Vehicle vehicle = vehicleRepository.findById(vehicle_id);
        vehicle.setStatus(status);
        return new ResponseEntity<>(vehicleRepository.save(vehicle), HttpStatus.OK);
    }

    public Date stringToDate(String date) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.parse(date);
    }

    public Integer getOptimalVehicleId(List<Vehicle> vehicles, Double weight) {
        Double buffer = 10.0;
        Double max = vehicles.get(0).getCapacity();
        Vehicle vehicle = vehicles.get(0);
        for (int i= 1; i< vehicles.size(); i++) {
            vehicle = vehicles.get(i);
            Double capacity = vehicle.getCapacity();
            if (capacity > weight && (capacity <= (weight + buffer)) && capacity < max) {
                max = capacity;
            }
        }

        return vehicle.getId();
    }
}
