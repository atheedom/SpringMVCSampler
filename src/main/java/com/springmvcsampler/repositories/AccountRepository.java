package com.springmvcsampler.repositories;

import javax.inject.Inject;

import com.springmvcsampler.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Repository("Account Repository")
public interface AccountRepository extends JpaRepository<Account, UUID> {

	@Query("FROM Account a WHERE a.id = :id")
    Account findById(@Param("id") UUID id);

    @Query("FROM Account a WHERE a.email = :email")
    Account findByEmail(@Param("email") String email);

    @Query("FROM Account a")
    Page<Account> findAll(Pageable pageable);

}
