package cn.hz.fcloud.entity;

import java.util.Date;

public class EquipmentData {
    private String code;
    private String info;
    private Date createTime;

    @Override
    public String toString() {
        return "EquipmentData{" +
                "code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public EquipmentData(String code, String info, Date createTime) {
        this.code = code;
        this.info = info;
        this.createTime = createTime;
    }

    public EquipmentData() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
