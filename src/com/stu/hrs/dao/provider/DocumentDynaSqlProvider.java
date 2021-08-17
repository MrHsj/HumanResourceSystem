package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.Document;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.DOCUMENT_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-01 11:17
 */
public class DocumentDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DOCUMENT_TABLE);
                if (params.get("document") != null) {
                    Document document = (Document)params.get("document");
                    if (document.getTitle() != null && !("").equals(document.getTitle())) {
                        WHERE(" title LIKE CONCAT ('%', #{document.title}, '%') ");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    public String count(Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DOCUMENT_TABLE);
                if (params.get("documemt") != null) {
                    Document document = (Document)params.get("document");
                    if (document.getTitle() != null && !("").equals(document.getTitle())) {
                        WHERE(" title LIKE CONCAT ('%', #{document.title}, '%') ");
                    }
                }
            }
        }.toString();
    }

    public String insertDocument(Document document) {
        return new SQL() {
            {
                INSERT_INTO(DOCUMENT_TABLE);
                if (document.getTitle() != null && !("").equals(document.getTitle())) {
                    VALUES("title", "#{title}");
                }
                if (document.getFileName() != null && !("").equals(document.getFileName())) {
                    VALUES("filename", "#{fileName}");
                }
                if (document.getRemark() != null && !("").equals(document.getRemark())) {
                    VALUES("remark", "#{remark}");
                }
                if (document.getUser() != null && document.getUser().getId() != null) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateDocument(Document document) {
        return new SQL() {
            {
                UPDATE(DOCUMENT_TABLE);
                if (document.getTitle() != null && !("").equals(document.getTitle())) {
                    SET("title = #{title}");
                }
                if (document.getFileName() != null && !("").equals(document.getFileName())) {
                    SET("filename = #{fileName}");
                }
                if (document.getRemark() != null && !("").equals(document.getRemark())) {
                    SET("remark = #{remark}");
                }
                if (document.getUser() != null && document.getUser().getId() != null) {
                    SET("user_id = #{user.id}");
                }
            }
        }.toString();
    }
}
