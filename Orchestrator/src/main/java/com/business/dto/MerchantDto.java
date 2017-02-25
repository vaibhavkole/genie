package com.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDto {
    @JsonProperty(value = "merchant_id")
    private Integer merchantId;
    @JsonProperty(value = "merchant_name")
    private String merchantName;
    @JsonProperty(value = "auth_code")
    private String authCode;
    @JsonProperty(value = "description")
    private String description;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "updated_at")
    private String updatedAt;
}