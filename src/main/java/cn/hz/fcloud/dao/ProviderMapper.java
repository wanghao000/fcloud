package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProviderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Provider record);

    int insertSelective(Provider record);

    Provider selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Provider record);

    int updateByPrimaryKey(Provider record);

    List<Provider> findAllProvider(Map<String,Object> map);

    int findAllProviderCount(Map<String,Object> map);

    int modifyState(@Param("id") int id, @Param("isDelete") int isDelete);

    List<Provider> selectAll();

    List<Provider> selectIdAndName();
}