package com.business.mapper;

import com.business.dto.MerchantDto;
import com.business.models.Merchant;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vaibhav.janardhan on 25/02/17.
 */
@Data
@Component
public class MerchantMapper implements BaseMapper<Merchant, MerchantDto> {

    @Autowired
    private ModelMapper modelMapper;

    public MerchantDto convertToDto(Merchant merchant) {
        MerchantDto merchantDto = modelMapper.map(merchant, MerchantDto.class);
        merchantDto.setMerchantId(merchant.getId());
        merchantDto.setMerchantName(merchant.getMerchantName());
        merchantDto.setDescription(merchant.getDescription());
        merchantDto.setAuthCode(merchant.getAuthCode());
        merchantDto.setCreatedAt(new Date(merchant.getCreatedAt().getTime()).toString());
        merchantDto.setUpdatedAt(new Date(merchant.getUpdatedAt().getTime()).toString());
        return merchantDto;
    }

    public Merchant convertToEntity(MerchantDto merchantDto) {
        Merchant merchant = modelMapper.map(merchantDto, Merchant.class);
        merchant.setMerchantName(merchantDto.getMerchantName());
        merchant.setDescription(merchantDto.getDescription());
        merchant.setAuthCode(merchantDto.getAuthCode());
        return merchant;
    }
}
