package com.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Data
public class AddressDto {
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "address_line1")
    private String addressLine1;
    @JsonProperty(value = "address_line2")
    private String addressLine2;
    @JsonProperty(value = "pincode")
    private String pincode;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "state")
    private String state;
    @JsonProperty(value = "primary_contact_number")
    private String primaryContactNumber;
    @JsonProperty(value = "alternate_contact_number")
    private String alternateContactNumber;
    @JsonProperty(value = "landmark")
    private String landmark;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
}
