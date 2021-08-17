package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.UserDynaSqlProvider;
import com.stu.hrs.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.USER_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-29 10:47
 */
public interface UserDAO {
    /**
     * 根据登录名和密码查询员工
     * @param loginName 登录名
     * @param password 登录密码
     * @return User
     */
    @Select("select * from " + USER_TABLE + " where loginname = #{loginName} and password = #{password}")
    User selectByLoginNameAndPassword(@Param("loginName") String loginName, @Param("password") String password);

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return User
     */
    @Select("select * from " + USER_TABLE + "where id = #{id}")
    User selectById(Integer id);


    /**
     * 根据用户id删除该用户
     * @param id 用户id
     */
    @Delete("delete from " + USER_TABLE + " where id = #{id}")
    void deleteById(Integer id);

    /**
     * 动态插入用户
     * @param user 包含要保存的用户信息
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "insertUser")
    void save(User user);

    /**
     * 动态修改用户
     * @param user 包含修改后的用户信息
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "updateUser")
    void update(User user);

    /**
     * 动态查询
     * @param params 参数
     * @return 用户查询结果
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String, Object> params);

    /**
     * 根据参数查询用户总数
     * @param params 参数
     * @return 用户总数
     */
    @SelectProvider(type = UserDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);
}
