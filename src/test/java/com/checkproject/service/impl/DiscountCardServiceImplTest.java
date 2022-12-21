package com.checkproject.service.impl;

import com.checkproject.dto.DiscountCardDto;
import com.checkproject.model.repository.DiscountCardRepository;
import com.checkproject.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class DiscountCardServiceImplTest {

    @MockBean
    private DiscountCardRepository discountCardRepository;
    @Autowired
    private DiscountCardService discountCardService;
    private DiscountCardDto discountCardDto;

    @BeforeEach
    void setUp() {
        Mockito.doReturn(true).when(discountCardRepository).existsByNumber(Mockito.isA(String.class));
        discountCardDto = new DiscountCardDto();
    }

    @Test
    void isExists() {
        discountCardDto.setNumber("1234");
        var exists = discountCardService.isExists(discountCardDto);
        assertTrue(exists);

    }

    @Test
    void isExistsNull() {
        var exists = discountCardService.isExists(discountCardDto);
        assertFalse(exists);
    }

}