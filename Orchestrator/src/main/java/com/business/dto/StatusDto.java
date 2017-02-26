package com.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * Created by vaibhav.janardhan on 26/02/17.
 */
@Data
public class StatusDto {
    @JsonProperty(value = "status")
    private String status;
}
