package com.stu.hrs.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author hsj
 * @version 1.0
 * @date 2021-07-28 20:09
 */
public class Document implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 描述
     */
    private String remark;

    /**
     * 上传时间
     */
    private String createDate;

    /**
     * 上传人id
     */
    private Integer userId;

    /**
     * 文件
     */
    private MultipartFile file;

    /**
     * 上传人
     */
    private User user;

    public Document() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
