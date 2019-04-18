package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysChinaMapper;
import cn.hz.fcloud.entity.SysChina;
import cn.hz.fcloud.service.SysChinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysChinaServiceImpl implements SysChinaService {

    @Autowired
    private SysChinaMapper mapper;

    @Override
    public List<SysChina> selectByParentid(int parentid){
        return mapper.selectByParentid(parentid);
    }

}
