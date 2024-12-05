package com.easybank.accounts.repository;

import com.easybank.accounts.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Accounts,Long> {

}
