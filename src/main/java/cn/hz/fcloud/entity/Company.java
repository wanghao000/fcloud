package cn.hz.fcloud.entity;

import java.util.Date;

public class Company {
    private Long id;

    private String name;

    private Integer province;

    private Integer city;

    private Integer district;

    private Long providerId;

    private String picture;

    private String address;

    private String remark;

    private Integer createUser;

    private String cName;

    private Date createTime;

    private Integer isDelete;

    private Integer eqsCount;

    private String code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getEqsCount() {
        return eqsCount;
    }

    public void setEqsCount(Integer eqsCount) {
        this.eqsCount = eqsCount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", providerId=" + providerId +
                ", picture='" + picture + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", createUser=" + createUser +
                ", cName='" + cName + '\'' +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", eqsCount=" + eqsCount +
                ", code=" + code +
                '}';
    }
}