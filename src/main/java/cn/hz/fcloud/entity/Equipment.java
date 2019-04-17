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
    private int create_user;
    private int is_online;
    private Date create_time;
    private int is_deleted;
    private int company_id;
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
                ", create_user=" + create_user +
                ", is_online=" + is_online +
                ", create_time=" + create_time +
                ", is_deleted=" + is_deleted +
                ", company_id=" + company_id +
                ", remark='" + remark + '\'' +
                '}';
    }

    public Equipment(String code, String name, int type, int province, int city, int district, String address, String lng, String lat, int create_user, int is_online, Date create_time, int is_deleted, int company_id, String remark) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.lng = lng;
        this.lat = lat;
        this.create_user = create_user;
        this.is_online = is_online;
        this.create_time = create_time;
        this.is_deleted = is_deleted;
        this.company_id = company_id;
        this.remark = remark;
    }

    public Equipment() {
    }

    public int getIs_online() {
        return is_online;
    }

    public void setIs_online(int is_online) {
        this.is_online = is_online;
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

    public int getCreate_user() {
        return create_user;
    }

    public void setCreate_user(int create_user) {
        this.create_user = create_user;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
