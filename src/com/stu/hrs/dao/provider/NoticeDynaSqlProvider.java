package com.stu.hrs.dao.provider;

import com.stu.hrs.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.stu.hrs.util.HrsConstants.NOTICE_TABLE;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-01 9:16
 */
public class NoticeDynaSqlProvider {

    public String selectWithParam(Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(NOTICE_TABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice)params.get("notice");
                    if (notice.getTitle() != null && !("").equals(notice.getTitle())) {
                        WHERE(" title LIKE CONCAT ('%', #{notice.title}, '%'");
                    }
                    if (notice.getContent() != null && !("").equals(notice.getContent())) {
                        WHERE(" content LIKE CONCAT ('%', #{notice.content}, '%'");
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
                FROM(NOTICE_TABLE);
                if (params.get("notice") != null) {
                    Notice notice = (Notice)params.get("notice");
                    if (notice.getTitle() != null && !("").equals(notice.getTitle())) {
                        WHERE(" title LIKE CONCAT ('%', #{notice.title}, '%'");
                    }
                    if (notice.getContent() != null && !("").equals(notice.getContent())) {
                        WHERE(" content LIKE CONCAT ('%', #{notice.content}, '%'");
                    }
                }
            }
        }.toString();
    }

    public String insertNotice(Notice notice) {
        return new SQL() {
            {
                INSERT_INTO(NOTICE_TABLE);
                if (notice.getTitle() != null && !("").equals(notice.getTitle())) {
                    VALUES("title", "#{title}");
                }
                if (notice.getContent() != null && !("").equals(notice.getContent())) {
                    VALUES("content", "#{content}");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    public String updateNotice(Notice notice) {
        return new SQL() {
            {
                UPDATE(NOTICE_TABLE);
                if (notice.getTitle() != null && !("").equals(notice.getTitle())) {
                    SET("title = #{title}");
                }
                if (notice.getContent() != null && !("").equals(notice.getContent())) {
                    SET("content = #{content}");
                }
                if (notice.getUser() != null && notice.getUser().getId() != null) {
                    SET("user_id = #{user.id}");
                }
                WHERE(" id = #{id}");
            }
        }.toString();
    }
}
