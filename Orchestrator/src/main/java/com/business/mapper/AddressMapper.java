package com.business.mapper;

import com.business.dto.AddressDto;
import com.business.models.Address;
import com.business.service.HttpRequestHandler;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Component
public class AddressMapper implements BaseMapper<Address, AddressDto> {

    @Autowired
    private ModelMapper modelMapper;


    public AddressDto convertToDto(Address address) {
        AddressDto addressDto = modelMapper.map(address, AddressDto.class);
        addressDto.setName(address.getName());
        addressDto.setAddressLine1(address.getAddressLine1());
        addressDto.setAddressLine2(address.getAddressLine2());
        addressDto.setPincode(address.getPincode());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        addressDto.setPrimaryContactNumber(address.getPrimaryContactNumber());
        addressDto.setAlternateContactNumber(address.getAlternateContactNumber());
        addressDto.setLandmark(address.getLandmark());
        addressDto.setEmail(address.getEmail());
        addressDto.setCreatedAt(new Date(address.getCreatedAt().getTime()).toString());
        addressDto.setUpdatedAt(new Date(address.getUpdatedAt().getTime()).toString());
        return addressDto;
    }

    public Address convertToEntity(AddressDto addressDto) {
        Address address = modelMapper.map(addressDto, Address.class);
        address.setName(addressDto.getName());
        address.setAddressLine1(addressDto.getAddressLine1());
        address.setAddressLine2(addressDto.getAddressLine2());
        address.setPincode(addressDto.getPincode());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPrimaryContactNumber(addressDto.getPrimaryContactNumber());
        address.setAlternateContactNumber(addressDto.getAlternateContactNumber());
        address.setLandmark(addressDto.getLandmark());
        address.setEmail(addressDto.getEmail());
        return address;
    }
}

