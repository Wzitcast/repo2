package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result( property = "username",column = "username"),
            @Result( property = "email",column = "email"),
            @Result( property = "password",column = "password"),
            @Result( property = "phoneNum",column = "phoneNum"),
            @Result( property = "status",column = "status"),
            @Result( property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select="com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))


    })
    public UserInfo findByUsername(String username)throws Exception;

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    //添加用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo user) throws Exception;

    //根据用户id查询用户信息
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "email",column = "email"),
            @Result(property = "password",column = "password"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class , many=@Many(select ="com.itheima.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findById(String id)throws Exception;

    @Select("select * from role where id not in( select roleId from users_role where userId=#{id})")
    public List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId")String roleId);
}
