package cn.hz.fcloud.service;

import cn.hz.fcloud.entity.Provider;

import java.util.List;

public interface ProviderService {
    Provider getProviderById(Long id);

    List<Provider> selectByCreateUser(Long create_user);

    int insert(Provider provider);
}
