package com.pyc.store.auth.repository;

import com.pyc.store.auth.entity.AuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Integer>{
    
    Optional<AuthUser> findUserName(String userName);
    
}
