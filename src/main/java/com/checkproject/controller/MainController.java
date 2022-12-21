package com.checkproject.controller;

import com.checkproject.dto.ProductDto;
import com.checkproject.dto.RequestDto;
import com.checkproject.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;

    @GetMapping(value = "/main")
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @GetMapping(value = "/main/{id}")
    public ProductDto getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PostMapping(value = "/main")
    public void createCheck(@RequestBody RequestDto requestDto){
        productService.createCheck(requestDto);
    }
}
