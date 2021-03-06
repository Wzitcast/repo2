package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll() throws Exception;

    public void save(Role role);

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermission(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
