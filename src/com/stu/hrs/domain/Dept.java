package com.stu.hrs.domain;

import java.io.Serializable;

/**
 * @author hsj
 * @date 2021-07-28 17:56
 * @version 1.0
 */
public class Dept implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 详细描述
     */
    private String remark;

    public Dept(){
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
