package com.example.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmailId(String tempEmailId);

	public User findByEmailIdAndPassword(String tempEmailId, String tempPassword);
}
