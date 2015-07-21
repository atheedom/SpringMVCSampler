package com.springmvcsampler.repository;

import com.springmvcsampler.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("Account Repository")
public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query("FROM Account a WHERE a.id = :id")
    Account findById(@Param("id") Long id);

    @Query("FROM Account a WHERE a.email = :email")
    Account findByEmail(@Param("email") String email);

    @Query("FROM Account a WHERE a.username = :username")
    Account findByUsername(@Param("username") String username);

    @Query("FROM Account a")
    Page<Account> findAll(Pageable pageable);


    Optional<Account> findOneByEmail(String email);
}
