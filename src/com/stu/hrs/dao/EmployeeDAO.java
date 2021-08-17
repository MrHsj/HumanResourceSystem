package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.EmployeeDynaSqlProvider;
import com.stu.hrs.domain.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.EMPLOYEE_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-31 17:56
 */
public interface EmployeeDAO {
    /**
     * 动态查询员工总数
     * @param params 查询的参数
     * @return 返回查询总数
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 根据参数动态查询员工
     * @param params 查询的参数
     * @return 返回 查询到的员工对象列表
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "card_id", property = "cardId"),
            @Result(column = "post_code", property = "postCode"),
            @Result(column = "qq_num", property = "qqNum"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select="com.stu.hrm.dao.DeptDAO.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select="com.stu.hrm.dao.JobDAO.selectById", fetchType = FetchType.EAGER))
    })
    List<Employee> selectByPage(Map<String, Object> params);

    /**
     * 动态插入员工
     * @param employee 要插入的员工对象
     */
    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "insertEmployee")
    void save(Employee employee);

    /**
     * 根据员工id删除员工
     * @param id 员工id
     */
    @Delete("delete from " + EMPLOYEE_TABLE + " where id = #[id}")
    void deleteById(int id);

    /**
     * 根据id查询员工
     * @param id 员工id
     * @return 返回查询到的员工对象
     */
    @Select("select * from " + EMPLOYEE_TABLE + " where id= #{id}")
    @Results({
            @Result(id=true, column = "id", property = "id"),
            @Result(column = "card_id", property = "cardId"),
            @Result(column = "post_code", property = "postCode"),
            @Result(column = "qq_num", property = "qqNum"),
            @Result(column = "birthday", property = "birthday", javaType = java.util.Date.class),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "dept_id", property = "dept", one = @One(select="com.stu.hrs.dao.DeptDAO.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "job_id", property = "job", one = @One(select="com.stu.hrs.dao.JobDAO.selectById", fetchType = FetchType.EAGER))
    })
    Employee selectById(int id);

    @SelectProvider(type = EmployeeDynaSqlProvider.class, method = "updateEmployee")
    void update(Employee employee);
}
