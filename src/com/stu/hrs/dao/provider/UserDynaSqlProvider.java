package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.USER_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-29 11:10
 */
public class UserDynaSqlProvider {

    /** 动态插入
     * @param user User对象
     * @return 插入sql语句
     */
    public String insertUser(User user) {
        return new SQL() {
            {
                INSERT_INTO(USER_TABLE);
                if (user.getLoginName() != null) {
                    VALUES("loginname", "#{loginName}");
                }
                if (user.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
                if (user.getUserName() != null) {
                    VALUES("name", "#{name}");
                }
                if (user.getCreateDate() != null) {
                    VALUES("create_data", "#{createDate}");
                }
            }
        }.toString();
    }

    /**
     * 动态更新
     * @param user 更新后的用户对象
     * @return 返回sql
     */
    public String updateUser(User user) {
        return new SQL() {
            {
                UPDATE(USER_TABLE);
                if (user.getUserName() != null) {
                    SET(" username = # {username} ");
                }
                if (user.getLoginName() != null) {
                    SET(" Loginname = #{loginname} ");
                }
                if (user.getStatus() != null) {
                    SET(" status = #{status}");
                }
                if (user.getCreateDate() != null) {
                    SET(" create_date = #{createDate}");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }

    /**
     * 分页动态查询
     * @param params 参数
     * @return sql
     */
    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(USER_TABLE);
                if (params.get("user") != null) {
                    User user = (User)params.get("user");
                    if (user.getUserName() != null && !("").equals(user.getUserName())) {
                        WHERE(" username LIKE CONCAT ('%', #{user.userName}, '%') ");
                    }
                    if (user.getStatus() != null) {
                        WHERE(" status LIKE CONCAT ('%', #{user.status}, '%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    /**
     * 动态查询总数量
    */
    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(USER_TABLE);
                if (params.get("user") != null) {
                    User user = (User)params.get("user");
                    if (user.getUserName() != null && !("").equals(user.getUserName())) {
                        WHERE(" username LIKE CONCAT ('%', #{user.username}, '%') ");
                    }
                    if (user.getStatus() != null) {
                        WHERE(" status LIKE CONCAT ('%', #{user.status}, '%') ");
                    }
                }
            }
        }.toString();
    }
}
