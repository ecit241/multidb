package com.weimob.bs.multidb.dao.mysql.model.qs;

import com.weimob.bs.multidb.dao.mysql.model.BaseBean;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_qs")
public class QS extends BaseBean {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    private String id;

    @Column(name = "app_name")
    private String appName;
    @Column(name = "quest_url")
    private String questUrl;
    @Column(name = "msg_type")
    private Integer msgType;
    @Column(name = "create_by")
    private String createBy;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "last_modified_by")
    private String lastModifiedBy;
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getQuestUrl() {
        return questUrl;
    }

    public void setQuestUrl(String questUrl) {
        this.questUrl = questUrl;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public void setUpdateTime(Date date) {
        this.lastModifiedDate = date;
    }

    @Override
    public void setCreateTime(Date date) {
        this.createDate = date;
    }
}