package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.ProviderMapper;
import cn.hz.fcloud.entity.Provider;
import cn.hz.fcloud.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProviderServiceImpl implements ProviderService {
    @Autowired
    private ProviderMapper providerMapper;
    @Override
    public Provider getProviderById(Long id) {
        return providerMapper.selectByPrimaryKey(id);
    }
    @Override
    public List<Provider> findAllProvider(Map<String,Object> map){
        return providerMapper.findAllProvider(map);
    }
    @Override
    public int findAllProviderCount(Map<String,Object> map){return providerMapper.findAllProviderCount(map);}
    @Override
    public int insert(Provider provider){
        return providerMapper.insert(provider);
    }
    @Override
    public List<Provider> selectAll() {
        return providerMapper.selectAll();
    }


    @Override
    public int modifyState(int id,int isDelete){
        return providerMapper.modifyState(id,isDelete == 1?0:1);
    }

    @Override
    public int updateByPrimaryKeySelective(Provider provider){
        return providerMapper.updateByPrimaryKeySelective(provider);
    }

    public List<Provider> selectIdAndName(){
        return providerMapper.selectIdAndName();
    }

    @Override
    public String findProviderCode() {
        return providerMapper.findProviderCode();
    }

    @Override
    public Provider findProviderByName(String name) {
        return providerMapper.findProviderByName(name);
    }

    @Override
    public List<Map<String, Object>> getProviderRanking(int type) {
        return providerMapper.alarmRanking(type);
    }
}
