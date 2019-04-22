package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Company;

import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    int countAll();

    List<Company> companyListByProviderId(Long providerId);

    //获取所有企业,不包括禁用
    List<Company> companyList();
}