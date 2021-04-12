package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Replay;

@Repository
public interface ReplayRepository extends JpaRepository<Replay, Integer> {

}
