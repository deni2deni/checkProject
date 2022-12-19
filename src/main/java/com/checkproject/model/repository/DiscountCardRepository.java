package com.checkproject.model.repository;

import com.checkproject.model.entity.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {

    Boolean existsByNumber(String number);
}
