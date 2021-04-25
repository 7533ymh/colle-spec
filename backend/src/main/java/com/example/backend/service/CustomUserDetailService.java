package com.example.backend.service;

import com.example.backend.mapper.UserMapper;
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
                .orElseThrow(() -> new  IllegalStateException("계정이 존재하지 않거나 아아디 또는 비밀번호가 정확하지 않습니다."));
    }


}
