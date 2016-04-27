package com.weimob.bs.multidb.dao.mysql.sjsupport.setting;

/**
 * Created by Adam on 2016/4/5.
 */

public class DatabaseSetting {
    private String driver;
    private String url;
    private String username;
    private String password;
    private String tb;
    private String tbName;
    private String tbKey;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTb() {
        return tb;
    }

    public void setTb(String tb) {
        this.tb = tb;
    }

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getTbKey() {
        return tbKey;
    }

    public void setTbKey(String tbKey) {
        this.tbKey = tbKey;
    }
}
