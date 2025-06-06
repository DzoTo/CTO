package com.example.cto.repository;

import com.example.cto.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Integer> {
}
