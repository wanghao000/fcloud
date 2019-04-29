package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.CompanyInfosMapper;
import cn.hz.fcloud.entity.CompanyInfos;
import cn.hz.fcloud.service.CompanyInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyInfosServiceImpl implements CompanyInfosService {

    @Autowired
    private CompanyInfosMapper companyInfosMapper;
    @Override
    public CompanyInfos findCompanyInfos(int id){
        return companyInfosMapper.findCompanyInfos(id);
    }
}
