package com.gunjan.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gunjan.airesumeanalyzer.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}