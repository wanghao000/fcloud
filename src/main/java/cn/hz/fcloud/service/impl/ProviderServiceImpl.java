package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.ProviderMapper;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;
    @Override
    public Provider getProviderById(Long id) {
        return providerMapper.selectByPrimaryKey(id);
    }
}
