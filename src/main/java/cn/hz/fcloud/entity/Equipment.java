package cn.hz.fcloud.entity;

import java.util.Date;

public class Equipment {
    private int id;
    private String code;
    private String name;
    private int type;
    private int province;
    private int city;
    private int district;
    private String address;
    private String lng;
    private String lat;
    private int createUser;
    private int isOnline;
    private Date lastReportTime;
    private Date createTime;
    private int isDelete;
    private int companyId;
    private String remark;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", address='" + address + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", createUser=" + createUser +
                ", isOnline=" + isOnline +
                ", lastReportTime=" + lastReportTime +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", companyId=" + companyId +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Equipment(String code, int isOnline, Date lastReportTime) {
        this.code = code;
        this.isOnline = isOnline;
        this.lastReportTime = lastReportTime;
    }


    public Equipment(int id, String code, String name, int type, int province, int city, int district, String address, String lng, String lat, int createUser, int isOnline, Date lastReportTime, Date createTime, int isDelete, int companyId, String remark) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.type = type;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.createUser = createUser;
        this.isOnline = isOnline;
        this.lastReportTime = lastReportTime;
        this.createTime = createTime;

        this.isDelete = isDelete;
        this.companyId = companyId;
        this.remark = remark;
    }

    public Equipment() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLastReportTime() {
        return lastReportTime;
    }

    public void setLastReportTime(Date lastReportTime) {
        this.lastReportTime = lastReportTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getisDelete() {
        return isDelete;
    }

    public void setisDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}