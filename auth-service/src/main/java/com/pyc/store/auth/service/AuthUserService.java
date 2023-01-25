package com.pyc.store.auth.service;

import com.pyc.store.auth.dto.AuthUserDto;
import com.pyc.store.auth.dto.TokenDto;
import com.pyc.store.auth.entity.AuthUser;
import com.pyc.store.auth.repository.AuthUserRepository;
import com.pyc.store.auth.security.JwtProvider;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService {
    
    @Autowired
    AuthUserRepository authUserRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    JwtProvider jwtProvider;
    
    public AuthUser save(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findUserName(dto.getUserName());
        
        if(!user.isPresent()) 
            return null;
        
        String password = passwordEncoder.encode(dto.getPassword());
        
        AuthUser authUser = AuthUser.builder()
                .userName(dto.getUserName())
                .password(dto.getPassword())
                .build();
        
        return authUserRepository.save(authUser);
    }
    
    public TokenDto login(AuthUserDto dto) {
        Optional<AuthUser> user = authUserRepository.findUserName(dto.getUserName());
        
        if(!user.isPresent()) 
            return null;
        
        if(passwordEncoder.matches(dto.getPassword(), user.get().getPassword()))
            return new TokenDto(jwtProvider.createToken(user.get()));
        
        return null;
    }
    
    public TokenDto validate(String token) {
        if(!jwtProvider.validate(token))
            return null;
        
        String userName = jwtProvider.getUsernameFromToken(token);
        
        if(!authUserRepository.findUserName(userName).isPresent())
            return null;
        
        return new TokenDto(token);
    }
    
}
