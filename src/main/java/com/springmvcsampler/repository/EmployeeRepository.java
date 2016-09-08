package com.springmvcsampler.repository;

import com.springmvcsampler.model.Company;
import com.springmvcsampler.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("Employee Repository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("FROM Employee e WHERE e.id = :id")
    Optional<Employee> findById(@Param("id") Long id);

    @Query("FROM Employee e WHERE e.name = :name")
    Optional<Employee> findByName(@Param("name") String name);

    @Query("FROM Employee e")
    Page<Employee> findAll(Pageable pageable);

}
