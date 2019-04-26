package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.CompanyMapper;
import cn.hz.fcloud.entity.Company;
import cn.hz.fcloud.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public List<Company> findAllCompanys(Map<String,Object> map){
        return companyMapper.findAllCompanys(map);
    }

    @Override
    public int findAllCompanysCount(Map<String,Object> map){
        return companyMapper.findAllCompanysCount(map);
    }
    //根据服务商id获取所有企业信息及设备数量，包括禁用
    @Override
    public List<Company> findComsByProId(Map<String,Object> map){
        return companyMapper.findComsByProId(map);
    }

    @Override
    public int findComsByProIdCount(Map<String,Object> map){
        return companyMapper.findComsByProIdCount(map);
    }

    @Override
    public int modifyState(int id,int isDelete){
        return companyMapper.modifyState(id,isDelete == 1?0:1);
    }

    @Override
    public String findCompanyCode(){
        return companyMapper.findCompanyCode();
    }
}
