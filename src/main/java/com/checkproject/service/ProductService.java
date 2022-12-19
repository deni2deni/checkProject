package com.checkproject.service;

import com.checkproject.dto.CreateCheckDto;
import com.checkproject.dto.DiscountCardDto;
import com.checkproject.dto.ProductDto;
import com.checkproject.dto.RequestDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getProducts();

    ProductDto getProductById(Long id);

    void createCheck(RequestDto requestDto);

    List<ProductDto> createCheckDtoToProductDto(List<CreateCheckDto> createCheckDtoList);

    Boolean isDiscounted(List<ProductDto> productDtoList);

    void applyDefaultDiscount(List<ProductDto> productDtoList);

    void applyDiscountCard(DiscountCardDto discountCardDto, List<ProductDto> productDtoList);
}
