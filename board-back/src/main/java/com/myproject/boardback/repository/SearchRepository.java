package com.myproject.boardback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.boardback.entity.SearchLogEntity;

@Repository
public interface SearchRepository extends JpaRepository<SearchLogEntity, Integer>{
  
}
