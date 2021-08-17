package com.stu.hrs.util;

/**
 * 分页实体
 * @author hsj
 * @version 1.0
 * @date 2021-08-02 16:12
 */
public class PageModel {
    /**
     * 分页总数据条数
     */
    private int recordCount;
    /**
     * 当前页面
     */
    private int pageIndex;
    /**
     * 每页分多少条数据
     */
    private int pageSize = HrsConstants.PAGE_DEFAULT_SIZE = 4;
    /**
     * 总页数
     */
    private int totalSize;

    public int getRecordCount() {
        this.recordCount = Math.max(this.recordCount, 0);
        return this.recordCount;
    }

    public void setRecordCount(int recordCount) {
        // 设置总数据数
        this.recordCount = recordCount;
    }

    public int getPageIndex() {
        this.pageIndex = this.pageIndex <= 0 ? 1: this.pageIndex;
        this.pageIndex = Math.min(this.pageIndex, this.getTotalSize());
        return this.pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    /**
     * 获取单页的数据量
     * @return 单页数据数
     */
    public int getPageSize() {
        this.pageSize = Math.max(this.pageSize, HrsConstants.PAGE_DEFAULT_SIZE);
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取分页的总页数，计算方式为（数据总条数 - 1） / 单页数据量 + 1
     * @return 总页数
     */
    public int getTotalSize() {
        if (this.getRecordCount() < 0) {
            this.totalSize = 0;
        } else {
            this.totalSize = (this.getRecordCount() - 1) / this.getPageSize() + 1;
        }
        return totalSize;
    }

    /**
     * 获取分页查询的第一个数据位置
     * @return 返回分页查询首个位置
     */
    public int getFirstLimitParam() {
        return (this.getPageIndex() - 1) * this.getPageSize();
    }

}
