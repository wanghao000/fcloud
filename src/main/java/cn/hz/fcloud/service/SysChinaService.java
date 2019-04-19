package cn.hz.fcloud.service;


import cn.hz.fcloud.entity.SysChina;
import java.util.List;

public interface SysChinaService {

    public List<SysChina> selectByParentid(int parentid);
}
