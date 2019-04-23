package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.CompanyMapper;
import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Company> getCompanyListByProviderId(Long id) {
        return companyMapper.companyListByProviderId(id);
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Company> companyList(){
        return companyMapper.companyList();
    }

    //获取所有企业信息及设备数量，包括禁用
    @Override
    public List<Company> findAllCompanys(){
        return companyMapper.findAllCompanys();
    }
    //根据服务商id获取所有企业信息及设备数量，包括禁用
    @Override
    public List<Company> findComsByProId(Long id){
        return companyMapper.findComsByProId(id);
    }
}
