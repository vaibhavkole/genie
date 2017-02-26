package com.business.mapper;

import com.business.dto.MerchantDto;
import com.business.dto.ShipmentDto;
import com.business.dto.ShipmentStatusDetailDto;
import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Component
public class ShipmentStatusDetailMapper implements BaseMapper<ShipmentStatusDetail, ShipmentStatusDetailDto> {

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ShipmentStatusDetailDto convertToDto(ShipmentStatusDetail shipmentStatusDetail) {
        /*ShipmentStatusDetailDto shipmentStatusDetailDto = modelMapper.map(shipmentStatusDetail, ShipmentStatusDetailDto.class);
        MerchantDto merchantInfo = requestHandler.getMerchantById(shipmentStatusDetail.getMerchantId());
        shipmentStatusDetailDto.setMerchantName(merchantInfo.getMerchantName());
        shipmentStatusDetailDto.setShipmentRefNumber(shipmentStatusDetail.getShipmentRefNumber());
        shipmentStatusDetailDto.setPickupAddress(shipmentStatusDetail.convertToDto(shipmentStatusDetail.getPickupAddress()));
        shipmentStatusDetailDto.setDeliveryAddress(shipmentStatusDetail.convertToDto(shipmentStatusDetail.getDeliveryAddress()));
        shipmentStatusDetailDto.setVolumetricWeight(shipmentStatusDetail.getVolumetricWeight());
        shipmentStatusDetailDto.setCreatedAt(new Date(shipmentStatusDetail.getCreatedAt().getTime()).toString());
        shipmentStatusDetailDto.setUpdatedAt(new Date(shipmentStatusDetail.getUpdatedAt().getTime()).toString());*/
        //return shipmentStatusDetailDto;
        return null;
    }

    @Override
    public ShipmentStatusDetail convertToEntity(ShipmentStatusDetailDto dtoName) {
        return null;
    }
}
