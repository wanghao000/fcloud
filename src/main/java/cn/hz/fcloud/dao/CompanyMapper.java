package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.Company;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface CompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    int countAll();

    List<Company> companyListByProviderId(Long providerId);

    //获取所有企业,不包括禁用
    List<Company> companyList();
    //获取所有企业信息及设备数量，包括禁用
    List<Company> findAllCompanys(Map<String,Object> map);

    int findAllCompanysCount(Map<String,Object> map);
    //根据服务商id获取所有企业信息及设备数量，包括禁用
    List<Company> findComsByProId(Map<String,Object> map);

    int findComsByProIdCount(Map<String,Object> map);

    int modifyState(@Param("id") int id,@Param("isDelete") int isDelete);

    //获取最近生成的公司code
    String findCompanyCode();

    Company findCompanyByName(String name);
}