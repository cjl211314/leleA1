package com.lele.dao;

import com.lele.pojo.Role;
import com.lele.pojo.Userinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {


    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lele.dao.RoleDao.findRoleByUserId")),
    })
    public Userinfo findByUsername(String username);

    @Select("select * from users")
    List<Userinfo> findAll();

    @Insert("insert into users (username,password,email,phoneNum,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(Userinfo userinfo);

    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.lele.dao.RoleDao.findRoleByUserId")),
    })
    Userinfo findById(String id);

    @Select("select * from role where id not in (select roleid from users_role where userid = #{userid})")
    List<Role> findOtherRolers(String userId);

    @Insert("insert into users_role(userid,roleId)values(#{userIds},#{roleIds})")
    void addRoleToUser(@Param("userIds") String userId, @Param("roleIds") String roleId);

}
