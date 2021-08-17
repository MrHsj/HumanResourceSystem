package com.stu.hrs.dao;

import com.stu.hrs.dao.provider.DocumentDynaSqlProvider;
import com.stu.hrs.domain.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static com.stu.hrs.util.HrsConstants.DOCUMENT_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-01 11:16
 */
public interface DocumentDAO {

    /**
     * 动态查询文档
     * @param params 查询的条件和分页的依据
     * @return 返回查到的对象列表
     */
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "selectWithParam")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "create_date", property = "createDate", javaType = java.util.Date.class),
            @Result(column = "user_id", property = "user", one = @One(select = "com.stu.hrs.dao.UserDAO.selectById", fetchType = FetchType.EAGER)),
    })
    List<Document> selectByPage(Map<String, Object> params);

    /**
     * 动态查询文档的个数
     * @param params 查询条件
     * @return 文档的个数
     */
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "count")
    Integer count(Map<String, Object> params);

    /**
     * 新增文档
     * @param document 要新增的文档对象
     */
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "insertDocument")
    void save(Document document);

    /**
     * 根据id查询文档对象
     * @param id 文档id
     * @return 返回文档id对应的文档对象
     */
    @Select("select * from " + DOCUMENT_TABLE + " where id = #{id}")
    Document selectById(int id);

    /**
     * 根据id删除文档对象
     * @param id 文档id
     */
    @Delete("delete from " + DOCUMENT_TABLE + " where id = {#id}")
    void deleteById(int id);

    /**
     * 更新文档
     * @param document 包含更新信息的文档对象
     */
    @SelectProvider(type = DocumentDynaSqlProvider.class, method = "updateDocument")
    void update(Document document);

}
