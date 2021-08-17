package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.Dept;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.DEPT_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-30 11:34
 */
public class DeptDynaSqlProvider {
    /**
     * 动态查询
     * @param params dept对象
     * @return 查询的sql语句
     */
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DEPT_TABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept)params.get("dept");
                    if (dept.getName() != null && ("").equals(dept.getName())) {
                        WHERE(" name LIKE CONCAT ('%', #{dept.name}, '%' ");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize}";
        }
        return sql;
    }

    /**
     * 返回动态查询的结果的个数的sql
     * @param params 包含动态查询信息的对象
     * @return 返回对应的sql
     */
    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DEPT_TABLE);
                if (params.get("dept") != null) {
                    Dept dept = (Dept)params.get("dept");
                    if (dept.getName() != null && !("").equals(dept.getName())) {
                        WHERE(" name LIKE CONCAT('%', #{dept.name}, '%' ");
                    }
                }
            }
        }.toString();
    }

    /**
     * 返回动态插入的sql
     * @param dept 要插入的部门对象信息
     * @return 返回对应的sql
     */
    public String insertDept(Dept dept) {
        return new SQL() {
            {
                INSERT_INTO(DEPT_TABLE);
                if (dept.getName() != null && !("").equals(dept.getName())) {
                    VALUES("name", "#{name}");
                }
                if (dept.getRemark() !=null && !("").equals(dept.getRemark())) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    /**
     * 返回动态修改的sql
     * @param dept 要修改的部门对象信息
     * @return  返回对应的sql
     */
    public String updateDept(Dept dept) {
        return new SQL() {
            {
                UPDATE(DEPT_TABLE);
                if (dept.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (dept.getRemark() != null) {
                    SET(" remark = #{remark} ");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
