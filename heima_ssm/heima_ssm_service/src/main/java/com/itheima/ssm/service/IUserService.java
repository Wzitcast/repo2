package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    //查询所有用户
    public List<UserInfo> findAll() throws  Exception;
    //添加用户
    public void save(UserInfo user) throws Exception;
    //根据id查询用户信息
    public UserInfo findById(String id)throws Exception;

    public List<Role> findOtherRoles(String userid);

    public void addRoleToUser(String userId, String[] roleIds);
}
