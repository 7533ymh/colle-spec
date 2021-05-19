package com.example.backend.domain.user.mapper;

import com.example.backend.domain.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;


@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(id, pass, name,grade ,sex, mail, phone, objective, enterprise, role) VALUES (#{user.id}, #{user.pass}, #{user.name}, #{user.grade}, #{user.sex}, " +
            "#{user.mail}, #{user.phone} ,#{user.objective}, #{user.enterprise}, #{user.role})")
    @Options(useGeneratedKeys = true, keyProperty = "user.idx")
    int save(@Param("user") User user);

    @Update("UPDATE user SET id = #{user.id}, pass = #{user.pass}, name = #{user.name}, grade={user.grade}, sex = #{user.sex}, mail = #{user.mail}, phone = #{user.phone} ," +
            "objective = #{user.objective}, enterprise = #{user.enterprise} WHERE idx = #{user.idx}")
    void update(@Param("user") User user);

    @Update("UPDATE user SET college = #{user.college}, major= #{user.major}, grade=#{user.grade}, link=#{user.link} WHERE idx = #{user.idx} ")
    void updateLink(@Param("user") User user);

    @Delete("DELETE FROM user WHERE idx = #{idx} ")
    void delete(@Param("idx") int idx);

    @Select("SELECT idx FROM user WHERE id = #{id}")
    Optional<Integer> findIdxById(@Param("id") String id);

    @Select("SELECT * FROM user WHERE id = #{id}")
    Optional<User> findById(@Param("id") String id);

    @Select("SELECT * FROM user WHERE idx = #{idx}")
    Optional<User> findByIdx(@Param("idx") int idx);

    @Select("SELECT * FROM user WHERE link = #{link}")
    Optional<User> findByLink(@Param("link") int link);

    @Select("SELECT enterprise FROM user WHERE idx = #{idx}")
    String findEnterpriseByIdx(@Param("idx") int idx);

}
