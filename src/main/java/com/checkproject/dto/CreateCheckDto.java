package com.checkproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCheckDto {
    private Long id;
    private Long quantity;
}
