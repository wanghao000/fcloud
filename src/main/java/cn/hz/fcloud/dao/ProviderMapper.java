package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
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

    //获取最后生成的服务商编号
    String findProviderCode();

    /**
     * 输入服务商名字判断服务商是否存在
     * @param name 服务商名字
     * @return
     */
    Provider findProviderByName(String name);

    /**
     * 服务商报警排行
     * @param type
     * @return
     */
    List<Map<String,Object>> alarmRanking(int type);

    List<Map<String,Object>> providerRanking();
}