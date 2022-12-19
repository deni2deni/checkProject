package com.checkproject.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DiscountCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String number;
}
