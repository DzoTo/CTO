package com.example.cto.repository;

import com.example.cto.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Optional<Request> findById(Long id);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM requests WHERE user_id = :userId AND status != 500",
            countQuery = "SELECT count(*) FROM requests WHERE user_id = :userId AND status != 500"
    )
    Page<Request> findAllAccepted(@Param("userId") Long userId, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM requests WHERE user_id = :userId AND status = 500",
            countQuery = "SELECT count(*) FROM requests WHERE user_id = :userId AND status = 500"
    )
    Page<Request> findAllRejected(@Param("userId") Long userId, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM requests WHERE user_id = :userId AND status != 500",
            countQuery = "SELECT count(*) FROM requests WHERE user_id = :userId"
    )
    Page<Request> findAllByUserId(@Param("userId") Long userId, Pageable pageable);
}
