package com.checkproject.service.impl;

import com.checkproject.constants.Constants;
import com.checkproject.dto.CreateCheckDto;
import com.checkproject.dto.DiscountCardDto;
import com.checkproject.dto.ProductDto;
import com.checkproject.dto.RequestDto;
import com.checkproject.mapper.ProductMapper;
import com.checkproject.model.repository.ProductRepository;
import com.checkproject.service.DiscountCardService;
import com.checkproject.service.ProductService;
import com.checkproject.utils.CheckBuilderUtil;
import com.checkproject.utils.CheckSaverUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CheckBuilderUtil checkBuilderUtil;
    private final DiscountCardService discountCardService;
    private final CheckSaverUtil checkSaverUtil;

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return productMapper.toDto(productRepository.getReferenceById(id));
    }

    @Override
    public void createCheck(RequestDto requestDto) {
        var productDtoList = createCheckDtoToProductDto(requestDto.getCreateCheckDtoArrayList());
        applyDiscountCard(requestDto.getDiscountCardDto(), productDtoList);
        applyDefaultDiscount(productDtoList);
        var check = new StringBuilder(checkBuilderUtil.buildHeader());
        var total = 0.0;
        for (ProductDto productDto : productDtoList) {
            total += productDto.getPrice() * productDto.getQuantity() * (1 - productDto.getDiscountRate());
            var productAppend = String.format(Locale.ENGLISH, "%-10s %5d %10.2f %10d%% %20.2f\n", productDto.getProductName(), productDto.getQuantity()
                    , productDto.getPrice(), (int) (productDto.getDiscountRate() * 100), productDto.getPrice() * productDto.getQuantity() * (1 - productDto.getDiscountRate()));
            check.append(productAppend);
        }
        check.append(checkBuilderUtil.buildFooter(total));
        System.out.println(check);
        try {
            checkSaverUtil.saveToTxt(check.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<ProductDto> createCheckDtoToProductDto(List<CreateCheckDto> createCheckDtoList) {
        var productDtoList = new ArrayList<ProductDto>();
        for (CreateCheckDto createCheckDto : createCheckDtoList) {
            var product = getProductById(createCheckDto.getId());
            product.setQuantity(createCheckDto.getQuantity());
            productDtoList.add(product);
        }
        return productDtoList;
    }

    @Override
    public Boolean isDiscounted(List<ProductDto> productDtoList) {
        var count = productDtoList.stream().filter(ProductDto::getDiscount).count();
        return count >= Constants.DISCOUNT_CONDITION;
    }

    @Override
    public void applyDefaultDiscount(List<ProductDto> productDtoList) {
        if (isDiscounted(productDtoList)) {
            for (ProductDto productDto : productDtoList) {
                if (productDto.getDiscount()) {
                    productDto.setDiscountRate(Constants.DEFAULT_DISCOUNT_RATE);
                }
            }
        }
    }

    @Override
    public void applyDiscountCard(DiscountCardDto discountCardDto, List<ProductDto> productDtoList) {
        if (discountCardDto != null && discountCardService.isExists(discountCardDto)) {
            for (ProductDto productDto : productDtoList) {
                productDto.setDiscountRate(Constants.DISCOUNT_CARD_RATE);
            }
        }
    }
}
