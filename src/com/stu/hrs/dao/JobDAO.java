package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.JobDynaSqlProvider;
import com.stu.hrs.domain.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.JOB_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-31 16:00
 */
public interface JobDAO {

    /**
     * 根据职位id查询对应的职位信息
     * @param id 职位id
     * @return 返回职位对象
     */
    @Select("select * from " + JOB_TABLE + " where id = #[id}")
    Job selectById(int id);

    /**
     * 查询所有的职位信息
     * @return 包含所有职位信息的列表，列表每一项为一个Job对象
     */
    @Select("select * from " + JOB_TABLE)
    List<Job> selectAllJob();

    /**
     * 动态查询
     * @param params Map类型，可包含查询的信息和分页的对象
     * @return 返回动态查询的结果
     */
    @SelectProvider(type = JobDynaSqlProvider.class, method = "selectWithParam")
    List<Job> selectByPage(Map<String, Object> params);


    /**
     * 根据动态条件查询
     * @param params  动态分页查询的问题
     * @return 返回符合条件的所有信息
     */
    @SelectProvider(type = JobDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 根据职位id删除对应职位
     */
    @Delete("delete from " + JOB_TABLE + "where id = #{id}")
    void deleteById(int id);

    /**
     * 动态保存职位
     * @param job 新增的职位对象
     */
    @SelectProvider(type = JobDynaSqlProvider.class, method = "insertJob")
    void save(Job job);

    /**
     * 动态修改职位
     * @param job 修改后的职位信息保存在job中
     */
    @SelectProvider(type = JobDynaSqlProvider.class, method = "updateJob")
    void update(Job job);
}
