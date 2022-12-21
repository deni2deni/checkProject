package com.checkproject.service.impl;

import com.checkproject.constants.Constants;
import com.checkproject.dto.CreateCheckDto;
import com.checkproject.dto.DiscountCardDto;
import com.checkproject.dto.ProductDto;
import com.checkproject.dto.RequestDto;
import com.checkproject.mapper.ProductMapper;
import com.checkproject.model.entity.Product;
import com.checkproject.model.repository.ProductRepository;
import com.checkproject.service.DiscountCardService;
import com.checkproject.service.ProductService;
import com.checkproject.utils.CheckBuilderUtil;
import com.checkproject.utils.CheckSaverUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RequiredArgsConstructor
class ProductServiceImplTest {

    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CheckBuilderUtil checkBuilderUtil;
    @MockBean
    private DiscountCardService discountCardService;
    @MockBean
    private CheckSaverUtil checkSaverUtil;
    @Autowired
    private ProductService productService;
    private Product product;
    private List<Product> productList;
    private List<ProductDto> productDtoList;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .productName("test")
                .discount(false)
                .price(1.0f)
                .build();
        productList = Collections.singletonList(product);
        var productDto = productMapper.toDto(product);
        productDtoList = Collections.singletonList(productDto);

    }

    @Test
    void getProducts() {
        Mockito.doReturn(productList).when(productRepository).findAll();
        var products = productService.getProducts();
        assertFalse(products.get(0).getDiscount());

    }

    @Test
    void getProductById() {
        Mockito.doReturn(product).when(productRepository).getReferenceById(Mockito.isA(Long.class));
        var product = productService.getProductById(1L);
        assertFalse(product.getDiscount());
    }

    @Test
    void createCheck() {
        productService = Mockito.mock(ProductServiceImpl.class);
        Mockito.doReturn(productDtoList).when(productService).createCheckDtoToProductDto(Mockito.anyList());
        Mockito.doNothing().when(productService).applyDefaultDiscount(Mockito.anyList());
        Mockito.doNothing().when(productService).applyDiscountCard(Mockito.isA(DiscountCardDto.class), Mockito.anyList());
        productService.createCheck(new RequestDto());
        Mockito.verify(productService, Mockito.times(1)).createCheck(Mockito.isA(RequestDto.class));
    }

    @Test
    void createCheckDtoToProductDto() {
        productService = Mockito.mock(ProductServiceImpl.class);
        Mockito.doReturn(productMapper.toDto(product)).when(productService).getProductById(Mockito.isA(Long.class));
        var createCheckDto = CreateCheckDto.builder()
                .quantity(1L)
                .id(1L)
                .build();
        var createCheckDtoList = Collections.singletonList(createCheckDto);
        var productDtoList = productService.createCheckDtoToProductDto(createCheckDtoList);
        Mockito.verify(productService, Mockito.times(1)).createCheckDtoToProductDto(Mockito.anyList());
    }

    @Test
    void isDiscounted() {
        var productDto = productMapper.toDto(product);
        productDto.setDiscount(true);
        productDtoList = new ArrayList<ProductDto>();
        for (int i = 0; i < 5; i++) {
            productDtoList.add(productDto);
        }
        var test = productService.isDiscounted(productDtoList);
        assertTrue(test);
    }

    @Test
    void applyDefaultDiscount() {
        productService = Mockito.mock(ProductServiceImpl.class);
        Mockito.doReturn(true).when(productService).isDiscounted(Mockito.anyList());
        productDtoList.get(0).setDiscount(true);
        productService.applyDefaultDiscount(productDtoList);
        Mockito.verify(productService, Mockito.times(1)).applyDefaultDiscount(Mockito.anyList());
    }

    @Test
    void applyDiscountCard() {
        Mockito.doReturn(true).when(discountCardService).isExists(Mockito.isA(DiscountCardDto.class));
        var discountCardDto = new DiscountCardDto();
        productService.applyDiscountCard(discountCardDto, productDtoList);
        assertEquals(Constants.DISCOUNT_CARD_RATE, productDtoList.get(0).getDiscountRate());
    }
}