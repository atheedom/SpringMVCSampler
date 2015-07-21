package com.springmvcsampler.repository;

import com.springmvcsampler.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("Project Repository")
public interface ProjectRepository extends JpaRepository<Project, Long> {

	@Query("FROM Project p WHERE p.id = :id")
    Optional<Project> findById(@Param("id") Long id);

    @Query("FROM Project p WHERE p.name = :name")
    Optional<Project> findByName(@Param("name") String name);

    @Query("FROM Project p WHERE p.clientName = :clientName")
    Page<Project> findByClientName(@Param("clientName") String clientName, Pageable pageable);

    @Query("FROM Project p")
    Page<Project> findAll(Pageable pageable);

}
