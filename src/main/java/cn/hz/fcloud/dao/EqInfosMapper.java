package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.EqInfos;

import java.util.List;

public interface EqInfosMapper {
//    List<EqInfos> findAll();
    EqInfos findOne(String code);
}
