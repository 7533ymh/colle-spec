package com.example.backend.domain.user.service;

import com.example.backend.global.exception.exception.CUserLoginFailException;
import com.example.backend.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userPK)  {
        return userMapper.findByIdx(Integer.parseInt(userPK))
                .orElseThrow(CUserLoginFailException::new);
    }


}
