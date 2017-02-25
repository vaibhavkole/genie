package com.business.mapper;

import com.business.dto.ShipmentDto;
import com.business.dto.ShipmentStatusDetailDto;
import com.business.models.Shipment;
import com.business.models.ShipmentStatusDetail;
import org.springframework.stereotype.Component;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Component
public class ShipmentStatusDetailMapper implements BaseMapper<ShipmentStatusDetail, ShipmentStatusDetailDto> {
    @Override
    public ShipmentStatusDetailDto convertToDto(ShipmentStatusDetail entityName) {
        return null;
    }

    @Override
    public ShipmentStatusDetail convertToEntity(ShipmentStatusDetailDto dtoName) {
        return null;
    }
}
