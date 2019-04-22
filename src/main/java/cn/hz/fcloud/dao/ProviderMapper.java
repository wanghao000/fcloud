package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Provider;

import java.util.List;

public interface ProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> selectByCreateUser(Long create_user);

}