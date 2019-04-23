package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> findAllProvider();

    int modifyState(@Param("id") int id, @Param("isDelete") int isDelete);

    List<Provider> selectAll();
}