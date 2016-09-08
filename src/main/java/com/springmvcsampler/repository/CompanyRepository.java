package com.springmvcsampler.repository;

import com.springmvcsampler.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("Company Repository")
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("FROM Company c WHERE c.id = :id")
    Optional<Company> findById(@Param("id") Long id);

    @Query("FROM Company c WHERE c.name = :name")
    Optional<Company> findByName(@Param("name") String name);

    @Query("FROM Company c")
    Page<Company> findAll(Pageable pageable);

}
