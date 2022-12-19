package com.checkproject.service.impl;

import com.checkproject.dto.DiscountCardDto;
import com.checkproject.model.repository.DiscountCardRepository;
import com.checkproject.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public Boolean isExists(DiscountCardDto discountCardDto) {
        return discountCardDto.getNumber() != null ? discountCardRepository.existsByNumber(discountCardDto.getNumber()) : false;
    }
}
