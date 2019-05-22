package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.EqMapper;
import cn.hz.fcloud.entity.Eq;
import cn.hz.fcloud.service.EqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EqServiceImpl implements EqService {

    @Autowired
    private EqMapper mapper;
    /**
     * 根据公司id获取设备信息以及是否告警
     * @param id 公司id
     * @return
     */
    public List<Eq> findByComId(Long id){
        return mapper.findByComId(id);
    }
}
