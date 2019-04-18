package cn.hz.fcloud.dao;

import cn.hz.fcloud.entity.SysChina;

import java.util.List;

public interface SysChinaMapper {

    List<SysChina> selectByParentid(int parentid);
}
