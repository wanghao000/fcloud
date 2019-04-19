package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Company;

import java.util.List;

public interface CompanyService {
    /**
     * 获取所有接入的企业
     * @return
     */
    int countAll();

    List<Company> getCompanyListByProviderId(Long id);

    Company getCompanyById(Long id);

    //运维账户获取所有公司信息
    List<Company> companyList();
}
