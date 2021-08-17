package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.JOB_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-31 16:11
 */
public class JobDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(JOB_TABLE);
                if (params.get("job") != null) {
                    Job job = (Job)params.get("job");
                    if(job.getName() != null && !("").equals(job.getName())) {
                        WHERE(" name LIKE CONCAT ('%', #{job.name}, '%");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " Limit #{pageModel.firstLimitParam, #{pageModel.pageSize)";
        }
        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(JOB_TABLE);
                if (params.get("job") != null) {
                    Job job = (Job)params.get("job");
                    if (job.getName() != null && !("").equals(job.getName())) {
                        WHERE(" name like CONCAT ('%', #job.getUName(); '%");
                    }
                }
            }
        }.toString();
    }

    public String insertJob(Job job) {
        return new SQL() {
            {
                INSERT_INTO(JOB_TABLE);
                if (job.getName() != null && !("").equals(job.getName())) {
                    VALUES("name", "#{name}");
                }
                if (job.getRemark() != null && !("").equals(job.getRemark())) {
                    VALUES("remark", "#{remark}");
                }
            }
        }.toString();
    }

    public String updateJob(Job job) {
        return new SQL() {
            {
                UPDATE(JOB_TABLE);
                if (job.getName() != null && !("").equals(job.getName())) {
                    SET(" name = #{name} ");
                }
                if (job.getRemark() != null && !("").equals((job.getRemark()))) {
                    SET(" remark = #{remark} ");
                }
            }
        }.toString();
    }
}
