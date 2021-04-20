package com.example.backend.mapper;

import com.example.backend.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {


    @Insert("INSERT INTO user(id, pass, sex, mail, phone, objective, enterprise) VALUES (#{user.id}, #{user.pass}, #{user.sex}, #{user.mail}, #{user.phone} ,#{user.objective}, #{user.enterprise})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("user") User user);



}
