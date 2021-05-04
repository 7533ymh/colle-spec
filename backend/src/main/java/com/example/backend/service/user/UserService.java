package com.example.backend.service.user;

import com.example.backend.advice.exception.CUserExistException;
import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {


    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public User signup(User user) {

        logger.info("회원가입");

        validateDuplicateUser(user.getId());

        userMapper.save(user);

        return user;

    }

    public void validateDuplicateUser(String id) {

        logger.info("아이디 중복 검사");

        userMapper.findById(id)
                .ifPresent(m -> {
                    throw new CUserExistException("이미 존재하는 회원입니다.");
                });

    }

    public User findById(String id) {

        logger.info("아이디를 통해 회원찾기");

        return userMapper.findById(id)
                .orElseThrow(() -> new CUserNotFoundException("해당 아이디의 계정 정보가 없습니다."));
    }

    public User modify(User user, String authId) {

        logger.info("회원정보 수정");

        if (!authId.equals(user.getId())) {
            validateDuplicateUser(user.getId());
        }

        userMapper.update(user);

        return user;

    }


    public void delete(int idx) {

        logger.info("회원 삭제");

        userMapper.delete(idx);

    }

    public int findIdxById(String id) {

        return userMapper.findIdxById(id)
                .orElseThrow(() -> new CUserNotFoundException("해당 아이디의 계정 정보가 없습니다."));

    }


}
