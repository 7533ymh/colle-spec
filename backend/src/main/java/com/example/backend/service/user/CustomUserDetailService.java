package com.example.backend.service.user;

import com.example.backend.advice.exception.CUserLoginFailException;
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
                .orElseThrow(CUserLoginFailException::new);
    }


}
