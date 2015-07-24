package com.springmvcsampler.repository;

import com.springmvcsampler.model.Employee;
import com.springmvcsampler.model.PayGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("Pay Grade Repository")
public interface PayGradeRepository extends JpaRepository<PayGrade, Long> {

	@Query("FROM PayGrade p WHERE p.id = :id")
    Optional<PayGrade> findById(@Param("id") Long id);

    @Query("FROM PayGrade p WHERE p.grade = :grade")
    Optional<PayGrade> findByGrade(@Param("grade") String grade);

    @Query("FROM PayGrade p")
    Page<PayGrade> findAll(Pageable pageable);

}
