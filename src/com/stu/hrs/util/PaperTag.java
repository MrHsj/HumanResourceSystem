package com.stu.hrs.util;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-08-02 17:48
 */
public class PaperTag extends SimpleTagSupport {
    /**
     * 定义请求URL的占位符常量
     */
    private static final String TAG = "{0}";
    /**
     * 当前页码
     */
    private int pageIndex;
    /**
     * 每页显示的数量
     */
    private int pageSize;
    /**
     * 总记录数
     */
    private int recordCount;
    /**
     * 请求URL page.action?pageIndex={0}
     */
    private String submitUrl;
    /**
     * 样式
     */
    private String style = "sabrosus";
    /**
     * 定义总页数
     */
    private int totalPage = 0;

    @Override
    public void doTag() throws JspException, IOException {
       // 定义它拼接最终的结果
        StringBuilder res = new StringBuilder();
        // 定义它拼接中间的代码
        StringBuilder str = new StringBuilder();
        // 判断总记录数
        if (recordCount > 0 ) {
            // 需要显示分页标签，计算出总页数，需要分多少页
            totalPage = (this.recordCount - 1) / this.pageSize + 1;
//            // 判断上一页或下一页需不需要加a标签
//            if (this.pageIndex == 1) {
//                str.append("<span class='disabled'>上一页</span>");
//                // 计算中间的页码
//                this.calcPage(str);
//                /// 下一页不需要a标签
//
//            }
            this.calcPage(str);
            this.getJspContext().getOut().print(str.toString());
        }
    }

    private void calcPage(StringBuilder str) {
        // 判断总页数
        if (this.totalPage <= 11) {
            // 一次性显示全部页码
            for (int i = 1; i <= this.totalPage; i++) {
                if (this.pageIndex == i) {
                    // 当前页
                    str.append("<span class='current'>" + i + "</span>");
                } else {
                    String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                    str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                }
            }
        } else {
            // 靠近首页
            if (this.pageIndex <= 8) {
               for (int i = 1; i <= 10; i++) {
                   if (this.pageIndex == i) {
                       str.append("<span class='current'>" + i + "</span>");
                   } else {
                       String tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                       str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                   }
               }
               str.append("...");
               String tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
               str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
            // 靠近尾页
            } else if (this.pageIndex + 8 >= this.totalPage) {
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + tempUrl +"'" + 1 + "</a>");
                str.append("...");
                for (int i = this.totalPage - 10; i <= this.totalPage; i ++) {
                    if (this.pageIndex == i) {
                        str.append("<span class='current'>" + i + "</span>");
                    } else {
                        tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + tempUrl + "'>" + i + "</span>");
                    }
                }
            // 在中间
            } else {
                String tempUrl = this.submitUrl.replace(TAG, String.valueOf(1));
                str.append("<a href='" + tempUrl +"'>1</a>" );
                str.append("...");
                for (int i = this.pageIndex - 4; i <= this.pageIndex + 4; i++) {
                    if (this.pageIndex == i) {
                        // 当前页
                        str.append("<span class='current'>" + i + "</span>");
                    } else {
                        tempUrl = this.submitUrl.replace(TAG, String.valueOf(i));
                        str.append("<a href='" + tempUrl + "'>" + i + "</a>");
                    }
                }
                str.append("...");
                tempUrl = this.submitUrl.replace(TAG, String.valueOf(this.totalPage));
                str.append("<a href='" + tempUrl + "'>" + this.totalPage + "</a>");
            }
        }
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public void setSubmitUrl(String submitUrl) {
        this.submitUrl = submitUrl;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
