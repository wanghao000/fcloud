package cn.hz.fcloud.entity;

import java.util.Date;
//设备表与省市县，公司关联实体
public class EqInfos {

    private int id;
    private String code;
    private String name;
    private int type;
    private int province;
    private String pName;
    private int city;
    private String cName;
    private int district;
    private String dName;
    private String address;
    private String lng;
    private String lat;
    private int createUser;
    private int isOnline;
    private Date lastReportTime;
    private Date createTime;
    private int isDelete;
    private int companyId;
    private String comName;
    private String remark;

    @Override
    public String toString() {
        return "EqInfos{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", province=" + province +
                ", pName='" + pName + '\'' +
                ", city=" + city +
                ", cName='" + cName + '\'' +
                ", district=" + district +
                ", dName='" + dName + '\'' +
                ", address='" + address + '\'' +
                ", lng='" + lng + '\'' +
                ", lat='" + lat + '\'' +
                ", createUser=" + createUser +
                ", isOnline=" + isOnline +
                ", lastReportTime=" + lastReportTime +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", companyId=" + companyId +
                ", comName='" + comName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
