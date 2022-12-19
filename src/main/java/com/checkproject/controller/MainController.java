package com.checkproject.controller;

import com.checkproject.constants.Constants;
import com.checkproject.dto.CreateCheckDto;
import com.checkproject.dto.DiscountCardDto;
import com.checkproject.dto.ProductDto;
import com.checkproject.dto.RequestDto;
import com.checkproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping(value = "/test")
    public List<ProductDto> getProducts() {
        System.out.println(Constants.CHECK_HEADER_PART1 + Constants.CHECK_HEADER_PART2 + Constants.CHECK_HEADER_PART3 + Constants.CHECK_BREAK_LINE);
        return productService.getProducts();
    }

    @GetMapping(value = "/test/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping(value = "/test")
    public void createCheck(@RequestBody RequestDto requestDto){
        productService.createCheck(requestDto);
    }
}
