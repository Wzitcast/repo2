package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户id查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
                    @Result(id=true,column="id",property="id"),
                    @Result(column="roleName",property="roleName"),
                    @Result(column="roleDesc",property="roleDesc"),
                    @Result(column="id",property="permissions",javaType=List.class,many=@Many(select="com.itheima.ssm.dao.IPermissionDao.findPermissionByRoleId"))
                            })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    //查询所有角色信息
    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId} )")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
