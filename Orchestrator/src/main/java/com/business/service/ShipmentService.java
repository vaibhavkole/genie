package com.business.service;

import com.business.dto.*;
import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import com.business.models.Status;
import com.business.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Service
public class ShipmentService {

    @Autowired
    ShipmentRepository shipmentRepository;

    @Autowired
    private HttpRequestHandler requestHandler;

    @Autowired
    HttpRequestHandler httpRequestHandler;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ShipmentStatusDetailService shipmentStatusDetailService;

    public Shipment createShipment(Shipment shipment) {
        ServiceArea serviceArea = getFacilityId(shipment.getPickupAddress().getPincode());
        ServiceArea serviceAreaDelivery = getFacilityId(shipment.getDeliveryAddress().getPincode());
        shipment.setPickupAddress(addressService.addAddress(shipment.getPickupAddress()));
        shipment.setDeliveryAddress(addressService.addAddress(shipment.getDeliveryAddress()));
        Shipment createdShipment = shipmentRepository.save(shipment);

        QuotationResponse quotationResponse = getQuatation(createdShipment, serviceArea.getHub_id(),serviceAreaDelivery.getHub_id());
        //call Facilities to book slot for delivery
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // Display a date in day, month, year format
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String today = formatter.format(date);
        bookReservation(serviceArea.getHub_id(),shipment.getVolumetricWeight(), today);
        calendar.add(Calendar.DAY_OF_YEAR,quotationResponse.getDays());
        date = calendar.getTime();
        String later = formatter.format(date);
        bookReservation(serviceAreaDelivery.getHub_id(),shipment.getVolumetricWeight(),later);
        //createPickupRequest();
        shipmentStatusDetailService.addShipmentStatus(createdShipment, StatusEnum.Shipment_Created, serviceArea.getHub_id(), serviceAreaDelivery.getHub_id(), serviceArea.getHub_id());
        return createdShipment;
    }

    private ServiceArea getFacilityId(String pincode) {
        ServiceArea serviceArea = httpRequestHandler.getServiceArea(pincode);
        return serviceArea;
    }
    private QuotationResponse getQuatation(Shipment shipment, int sourceHub, int destinationHub) {
        ShipmentCreationRequestDto shipmentCreationRequestDto = new ShipmentCreationRequestDto(shipment.getId(),
                sourceHub, destinationHub,shipment.getVolumetricWeight(), new Date().getTime());
        QuotationResponse quotationResponse = httpRequestHandler.getQuotationResponse(shipmentCreationRequestDto);
        return quotationResponse;
    }

    private Reservation bookReservation(Integer hub_id,  Double weight,  String date) {

        Reservation reservation = httpRequestHandler.bookReservation(hub_id,weight,date);
        return reservation;
    }

    public Shipment getShipmentDetails(String merchantName, String shipmentRefNumber){
        MerchantDto merchantInfo = requestHandler.getMerchantByName(merchantName);
        return shipmentRepository.findByMerchantIdAndShipmentRefNumber(merchantInfo.getMerchantId(), shipmentRefNumber);
    }

    public PickupRequestResponse createPickupRequest() {
        CreatePickupRequest createPickupRequest;
        int locationId= 23;
        createPickupRequest = new CreatePickupRequest();
        createPickupRequest.setShipmentId(12);
        createPickupRequest.setPincode(110032);
        createPickupRequest.setAddress("adfasd");
        return httpRequestHandler.createHttpPickupRequest(createPickupRequest,locationId);
    }

    public DeliverShipmentRequest createDeliverRequest(DeliverShipmentRequest deliverShipmentRequest, int locationId) {
        return httpRequestHandler.createHttpDeliverRequest(deliverShipmentRequest, locationId);
    }

    public List<String> mark_runsheet_complete(Integer runsheetId) {
        return httpRequestHandler.mark_runsheet_complete(runsheetId);
    }

    public List<String> mark_pickup_sheet_complete(Integer pickupSheetId) {
        return httpRequestHandler.mark_pickup_sheet_complete(pickupSheetId);
    }

    public Integer markPickupComplete(int shipmentId, int hubId) {
        return 0;
    }


    public Integer markDelivered(int shipmentId, int hubId) {
        return 0;
    }


    public Integer markReceived(int shipmentId, int hubId) {
        return 0;
    }



    public int getNextHub(int hubId, int shipmentId) {
        return httpRequestHandler.getNextHub(hubId, shipmentId);
    }
}
