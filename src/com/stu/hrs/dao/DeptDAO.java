package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.DeptDynaSqlProvider;
import com.stu.hrs.domain.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.DEPT_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-30 11:34
 */
public interface DeptDAO {
    /**
     * 动态查询
     * @param params 用于查询的信息，以及分页的信息，包含在dept和pageModel对象中
     * @return 查询的结果
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "selectWithParam")
    List<Dept> selectByPage(Map<String, Object> params);

    /**
     * 根据条件动态查询总数量
     * @param params 用于查询的信息，同上
     * @return 总数量
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 查询所有的部门
     * @return 返回所有的部门
     */
    @Select("select * from " + DEPT_TABLE)
    List<Dept> selectAllDept();
    /**
     * 根据部门id查询部门
     * @param id 部门id
     * @return 该部门id所对应的参数
     */
    @Select("select * from " + DEPT_TABLE + "where id= #{id}")
    Dept selectById(int id);

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    @Delete("delete from " + DEPT_TABLE +" where id = #{id}")
    void deleteById(Integer id);

    /**
     * 保存部门信息
     * @param dept 保存部门信息的对象
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "insertDept")
    void save(Dept dept);

    /**
     * 动态更新部门信息
     * @param dept 更新后的部门对象
     */
    @SelectProvider(type = DeptDynaSqlProvider.class, method = "updateDept")
    void update(Dept dept);

}
