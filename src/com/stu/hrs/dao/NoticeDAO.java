package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.NoticeDynaSqlProvider;
import com.stu.hrs.domain.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.NOTICE_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-01 9:14
 */
public interface NoticeDAO {

    /**
     * 动态查询
     * @param params 包含查询条件和分页的依据
     * @return 动态查询Notice的结果
     */
    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_date", property = "createDate"),
            @Result(column = "user_id", property = "user", one = @One(select = "com.stu.hrs.dao.UserDAO.selectById", fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String, Object> params);

    /**
     * 返会动态查询的结果个数
     * @param params 动态查询的参数
     * @return 查询结果数
     */
    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 根据通知id查询通知
     * @param id 通知id
     * @return 该id对应的通知对象
     */
    @Select("select * from " + NOTICE_TABLE + " where id =#{id}")
    Notice selectById(int id);

    /**
     * 根据id删除通知
     * @param id 通知id
     */
    @Delete("delete from " + NOTICE_TABLE + " where id = #{id}")
    void delete(int id);

    /**
     * 动态插入Notice对象
     * @param notice 要添加的Notice对象
     */
    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "insertNotice")
    void save(Notice notice);

    /**
     * 动态修改Notice对象
     * @param notice 已经修改过后的Notice对象
     */
    @SelectProvider(type = NoticeDynaSqlProvider.class, method = "updateNotice")
    void update(Notice notice);
}
