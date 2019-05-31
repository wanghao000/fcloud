package cn.hz.fcloud.entity;

import java.util.Date;

/**
 * 设备实体类
 */
public class Equipment {
    private Integer id;
    private String code;
    private String name;
    private Integer type;
    private Integer province;
    private Integer city;
    private Integer district;
    private String address;
    private String lng;
    private String lat;
    private Integer createUser;
    private Integer isOnline;
    private Date lastReportTime;
    private Date createTime;
    private Integer isDelete;
    private Integer companyId;
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

    public Equipment(String code, Integer isOnline, Date lastReportTime) {
        this.code = code;
        this.isOnline = isOnline;
        this.lastReportTime = lastReportTime;
    }


    public Equipment(Integer id, String code, String name, Integer type, Integer province, Integer city, Integer district, String address, String lng, String lat, Integer createUser, Integer isOnline, Date lastReportTime, Date createTime, Integer isDelete, Integer companyId, String remark) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProvince() {
        return province;
    }

    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
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

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
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

    public Integer getisDelete() {
        return isDelete;
    }

    public void setisDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}