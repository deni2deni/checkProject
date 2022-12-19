package com.checkproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private List<CreateCheckDto> createCheckDtoArrayList;
    private DiscountCardDto discountCardDto;
}
