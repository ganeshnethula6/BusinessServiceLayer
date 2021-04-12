package com.example.application.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.PromotionDetail;

@Repository
public interface ClientPromotionRepository extends JpaRepository<PromotionDetail, Integer> {

	List<PromotionDetail> findByAddType(String addType);
}
