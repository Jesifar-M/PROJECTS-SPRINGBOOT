package com.shop.identity.dao;

import com.shop.identity.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List; // 🟢 CRITICAL IMPORT

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // 🟢 The return type must strictly be List<Account> to avoid type mismatches
    @Query("SELECT a FROM Account a WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(a.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Account> searchAccounts(@Param("keyword") String keyword);
}
