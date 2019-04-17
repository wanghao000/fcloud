package cn.hz.fcloud.entity;

import java.util.Date;

public class EquipmentData {
    private int id;
    private String code;
    private String info;
    private int type;
    private Date createTime;

    @Override
    public String toString() {
        return "EquipmentData{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public EquipmentData(String code, String info, int type, Date createTime) {
        this.code = code;
        this.info = info;
        this.type = type;
        this.createTime = createTime;
    }

    public EquipmentData() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
