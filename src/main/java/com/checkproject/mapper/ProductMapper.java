package com.checkproject.mapper;

import com.checkproject.dto.ProductDto;
import com.checkproject.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
}
