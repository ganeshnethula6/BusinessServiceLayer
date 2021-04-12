package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.FileDetail;

@Repository
public interface FileRepository extends JpaRepository<FileDetail, Integer> {

}
