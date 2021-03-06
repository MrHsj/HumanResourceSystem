package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.Employee;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.EMPLOYEE_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-31 17:57
 */
public class EmployeeDynaSqlProvider {
    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(EMPLOYEE_TABLE);
                if (params.get("employee") != null) {
                    Employee employee = (Employee)params.get("employee");
                    if (employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
                        WHERE(" dept_id = #{employee.dept.id} ");
                    }
                    if (employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
                        WHERE(" job_id = #{employee.job.id} ");
                    }
                    if (employee.getName() != null && !("").equals(employee.getName())) {
                        WHERE(" name LIKE CONCAT ('%', #{employee.name}, '%') ");
                    }
                    if (employee.getPhone() !=null && !("").equals(employee.getPhone())) {
                        WHERE(" phone LIKE CONCAT ('%', #{employee.phone}, '%') ");
                    }
                    if (employee.getCardId() != null && !("").equals((employee.getCardId()))) {
                        WHERE("card_id LIKE CONCAT ('%', #{employee.cardId}, '%' ");
                    }
                    if (employee.getSex() != null && !("".equals(employee.getSex()))) {
                        WHERE("sex = #{employee.sex} ");
                    }
                }
            }
        }.toString();
    }

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(EMPLOYEE_TABLE);
                if (params.get("employee") != null) {
                    Employee employee = (Employee)params.get("employee");
                    if (employee.getDept() != null && employee.getDept().getId() != null && employee.getDept().getId() != 0) {
                        WHERE(" dept_id = #{employee.dept.id} ");
                    }
                    if (employee.getJob() != null && employee.getJob().getId() != null && employee.getJob().getId() != 0) {
                        WHERE(" job_id = #{employee.job.id} ");
                    }
                    if (employee.getName() != null && !("").equals(employee.getName())) {
                        WHERE(" name LIKE CONCAT ('%', #{employee.name}, '%') ");
                    }
                    if (employee.getPhone() !=null && !("").equals(employee.getPhone())) {
                        WHERE(" phone LIKE CONCAT ('%', #{employee.phone}, '%') ");
                    }
                    if (employee.getCardId() != null && !("").equals((employee.getCardId()))) {
                        WHERE("card_id LIKE CONCAT ('%', #{employee.cardId}, '%' ");
                    }
                    if (employee.getSex() != null && !("".equals(employee.getSex()))) {
                        WHERE("sex = #{employee.sex} ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += "limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String insertEmployee(Employee employee) {
        return new SQL() {
            {
                INSERT_INTO(EMPLOYEE_TABLE);
                if (employee.getName() != null) {
                    VALUES("name", "#{name}");
                }
                if (employee.getCardId() != null) {
                    VALUES("card-id", "#{cardId}");
                }
                if (employee.getAddress() != null) {
                    VALUES("address", "#{address}");
                }
                if (employee.getPostCode() != null) {
                    VALUES("post_code", "#{post_code}");
                }
                if (employee.getTel() != null) {
                    VALUES("tel", "#{tel}");
                }
                if (employee.getPhone() != null) {
                    VALUES("phone", "#{phone}");
                }
                if (employee.getQqNum() != null) {
                    VALUES("qq_num", "#{qqNum}");
                }
                if (employee.getEmail() != null) {
                    VALUES("email", "#{email}");
                }
                if (employee.getSex() != null) {
                    VALUES("sex", "#{sex}");
                }
                if (employee.getParty() != null) {
                    VALUES("party", "#{party}");
                }
                if (employee.getBirthday() != null) {
                    VALUES("race", "#{race}");
                }
                if (employee.getEducation() != null) {
                    VALUES("education", "#{education}");
                }
                if (employee.getSpeciality() != null) {
                    VALUES("speciality", "#{speciality}");
                }
                if (employee.getHobby() != null) {
                    VALUES("hobby", "#{hobby}");
                }
                if (employee.getRemark() != null) {
                    VALUES("remark", "#{remark}");
                }
                if (employee.getCreateDate() != null) {
                    VALUES("create_date", "#{createDate");
                }
                if (employee.getDept() != null) {
                    VALUES("dept_id", "#{dept.id}");
                }
                if (employee.getJob() != null) {
                    VALUES("job_id", "#{job.id}");
                }
            }
        }.toString();
    }

    public String updateEmployee(Employee employee) {
        return new SQL() {
            {
                UPDATE(EMPLOYEE_TABLE);
                if (employee.getName() != null) {
                    SET(" name = #{name} ");
                }
                if (employee.getCardId() != null) {
                    SET("card-id = #{cardId}");
                }
                if (employee.getAddress() != null) {
                    SET("address = #{address}");
                }
                if (employee.getPostCode() != null) {
                    SET("post_code = #{post_code}");
                }
                if (employee.getTel() != null) {
                    SET("tel = #{tel}");
                }
                if (employee.getPhone() != null) {
                    SET("phone = #{phone}");
                }
                if (employee.getQqNum() != null) {
                    SET("qq_num = #{qqNum}");
                }
                if (employee.getEmail() != null) {
                    SET("email = #{email}");
                }
                if (employee.getSex() != null) {
                    SET("sex = #{sex}");
                }
                if (employee.getParty() != null) {
                    SET("party = #{party}");
                }
                if (employee.getBirthday() != null) {
                    SET("race = #{race}");
                }
                if (employee.getEducation() != null) {
                    SET("education = #{education}");
                }
                if (employee.getSpeciality() != null) {
                    SET("speciality = #{speciality}");
                }
                if (employee.getHobby() != null) {
                    SET("hobby = #{hobby}");
                }
                if (employee.getRemark() != null) {
                    SET("remark = #{remark}");
                }
                if (employee.getCreateDate() != null) {
                    SET("create_date = #{createDate");
                }
                if (employee.getDept() != null) {
                    SET("dept_id = #{dept.id}");
                }
                if (employee.getJob() != null) {
                    SET("job_id = #{job.id}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
