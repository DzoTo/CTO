package com.example.cto.repository;

import com.example.cto.model.Request;
import com.example.cto.model.RequestHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Integer> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM request_history WHERE request_id = :requestId",
            countQuery = "SELECT count(*) FROM request_history WHERE request_id = :requestId"
    )
    Page<RequestHistory> findAllByRequestId(@Param("requestId") Long requestId, Pageable pageable);
}
