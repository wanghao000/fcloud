package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Company;

import java.util.List;
import java.util.Map;

public interface CompanyService {

    List<Company> getCompanyListByProviderId(Long id);

    Company getCompanyById(Long id);

    //获取所有公司信息，不包含禁用的
    List<Company> companyList();
    //获取所有企业信息及设备数量，包括禁用
    List<Company> findAllCompanys(Map<String,Object> map);

    int findAllCompanysCount(Map<String,Object> map);
    //根据服务商id获取所有企业信息及设备数量，包括禁用
    List<Company> findComsByProId(Map<String,Object> map);

    int findComsByProIdCount(Map<String,Object> map);

    int modifyState(int id,int isDelete);

    String findCompanyCode();
}
