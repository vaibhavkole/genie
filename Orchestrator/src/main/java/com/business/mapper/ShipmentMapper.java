package com.business.mapper;

import com.business.dto.MerchantDto;
import com.business.dto.ShipmentDto;
import com.business.models.Shipment;
import com.business.service.HttpRequestHandler;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Component
public class ShipmentMapper implements BaseMapper<Shipment, ShipmentDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private HttpRequestHandler requestHandler;


    public ShipmentDto convertToDto(Shipment shipment) {
        ShipmentDto shipmentDto = modelMapper.map(shipment, ShipmentDto.class);
        MerchantDto merchantInfo = requestHandler.getMerchantById(shipment.getMerchantId());
        shipmentDto.setMerchantName(merchantInfo.getMerchantName());
        shipmentDto.setShipmentRefNumber(shipment.getShipmentRefNumber());
        shipmentDto.setPickupAddress(addressMapper.convertToDto(shipment.getPickupAddress()));
        shipmentDto.setDeliveryAddress(addressMapper.convertToDto(shipment.getDeliveryAddress()));
        shipmentDto.setVolumetricWeight(shipment.getVolumetricWeight());
        shipmentDto.setCreatedAt(new Date(shipment.getCreatedAt().getTime()).toString());
        shipmentDto.setUpdatedAt(new Date(shipment.getUpdatedAt().getTime()).toString());
        return shipmentDto;
    }

    public Shipment convertToEntity(ShipmentDto shipmentDto) {
        Shipment shipment = modelMapper.map(shipmentDto, Shipment.class);
        MerchantDto merchantInfo = requestHandler.getMerchantByName(shipmentDto.getMerchantName());
        shipment.setMerchantId(merchantInfo.getMerchantId());
        shipment.setShipmentRefNumber(shipmentDto.getShipmentRefNumber());
        shipment.setPickupAddress(addressMapper.convertToEntity(shipmentDto.getPickupAddress()));
        shipment.setDeliveryAddress(addressMapper.convertToEntity(shipmentDto.getDeliveryAddress()));
        shipment.setVolumetricWeight(shipmentDto.getVolumetricWeight());
        return shipment;
    }
}
