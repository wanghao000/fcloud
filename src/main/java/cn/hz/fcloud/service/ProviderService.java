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

    //获取最后生成的服务商编号
    String findProviderCode();


    /**
     * 输入服务商名字判断服务商是否存在
     * @param name 服务商名字
     * @return
     */
    Provider findProviderByName(String name);

    /**
     * 服务商报警排行服务
     * @param type
     * @return
     */
    List<Map<String,Object>>  getProviderRanking(int type);

    /**
     * 获取服务商的排名
     * @return
     */
    List<Map<String,Object>> getCompanyRanking();
}
