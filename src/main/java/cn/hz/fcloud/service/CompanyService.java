package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Company;

import java.util.List;

public interface CompanyService {

    List<Company> getCompanyListByProviderId(Long id);

    Company getCompanyById(Long id);

    //获取所有公司信息，不包含禁用的
    List<Company> companyList();
}
