package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Provider;

import java.util.List;

public interface ProviderService {
    Provider getProviderById(Long id);

    List<Provider> findAllProvider();

    int insert(Provider provider);

    int modifyState(int id,int isDelete);
}
