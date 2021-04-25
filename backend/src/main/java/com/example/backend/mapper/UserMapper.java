package com.example.backend.mapper;

import com.example.backend.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(id, pass, name, sex, mail, phone, objective, enterprise) VALUES (#{user.id}, #{user.pass}, #{user.name}, #{user.sex}, #{user.mail}, #{user.phone} ,#{user.objective}, #{user.enterprise})")
    @Options(useGeneratedKeys = true, keyProperty = "idx")
    int insert(@Param("user") User user);


    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<User> findById(@Param("id") String id);

    @Select("SELECT * FROM user WHERE id = #{id} AND pass = #{pass}")
    Optional<User> findByIdPass(@Param("id") String id, @Param("pass") String pass);




}
