package cn.hz.fcloud.entity;

import java.util.Date;
import java.util.List;

public class Provider {
    private Long id;

    private String name;

    private String serviceTel;

    private Integer province;

    private String pName;

    private Integer city;

    private String cName;

    private Integer district;

    private String dName;

    private String contacts;

    private String contactsPhone;

    private String address;

    private String remark;

    private Long createUser;

    private Date createTime;

    private Integer isDelete;

    private Integer eqsCount;

    private List<Company> children;

    private boolean open;

    public boolean isParent = true;

    public Integer getEqsCount() {
        return eqsCount;
    }

    public void setEqsCount(Integer eqsCount) {
        this.eqsCount = eqsCount;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public String getServiceTel() {
        return serviceTel;
    }

    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel == null ? null : serviceTel.trim();
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

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
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

    public List<Company> getChildren() {
        return children;
    }

    public void setChildren(List<Company> children) {
        this.children = children;
    }

    public boolean isOpen() {
        return true;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }



    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceTel='" + serviceTel + '\'' +
                ", province=" + province +
                ", pName='" + pName + '\'' +
                ", city=" + city +
                ", cName='" + cName + '\'' +
                ", district=" + district +
                ", dName='" + dName + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contactsPhone='" + contactsPhone + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", isDelete=" + isDelete +
                ", eqsCount=" + eqsCount +
                '}';
    }
}