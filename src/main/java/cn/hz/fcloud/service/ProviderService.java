package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Provider;

import java.util.List;
import java.util.Map;

public interface ProviderService {
    Provider getProviderById(Long id);

    List<Provider> findAllProvider(Map<String,Object> map);

    int findAllProviderCount(Map<String,Object> map);

    int insert(Provider provider);

    int modifyState(int id,int isDelete);
    List<Provider> selectAll();
    int updateByPrimaryKeySelective(Provider provider);

    List<Provider> selectIdAndName();
}
