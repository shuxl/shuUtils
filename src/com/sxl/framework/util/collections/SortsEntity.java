package com.sxl.framework.util.collections;

import java.util.Date;

/**
 * @author shuxiaolong
 * @time 2018/12/26 15:56
 * @description
 */
public class SortsEntity {
    private Integer sort1;
    private boolean sort2;
    private Date createTime;

    public SortsEntity(Integer sort1, boolean sort2, Date createTime) {
        this.sort1 = sort1;
        this.sort2 = sort2;
        this.createTime = createTime;
    }

    public Integer getSort1() {
        return sort1;
    }

    public void setSort1(Integer sort1) {
        this.sort1 = sort1;
    }

    public boolean isSort2() {
        return sort2;
    }

    public void setSort2(boolean sort2) {
        this.sort2 = sort2;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SortsEntity{" +
                "sort1=" + sort1 +
                ", sort2=" + sort2 +
                ", createTime=" + (null == createTime ? "" : (createTime.getYear() +"-"+ createTime.getMonth() +"-"+ createTime.getDate())) +
                '}';
    }
}
