package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRespository extends JpaRepository<FeedBack, Long> {
}
