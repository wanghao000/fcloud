package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysUserMapper;
import cn.hz.fcloud.entity.SysUser;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.ServiceException;
import cn.hz.fcloud.utils.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryByUserName(String username) {
        return sysUserMapper.queryByUserName(username);
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return sysUserMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return sysUserMapper.queryAllMenuId(userId);
    }

    @Override
    public List<SysUser> queryList(Map<String, Object> map) {
        List<SysUser> userList = null;
        SysUser user = ShiroUtil.getUserEntity();
        if(user.getType() == 1){
            userList = sysUserMapper.queryList(map);
        }else if(user.getType() == 2){
            //先查询服务商下的所有企业,根据企业ID匹配用户账号
        }
        return userList;
    }

    @Override
    public int save(SysUser user) {
        String username = user.getUsername();
        if(username == null || StringUtils.isBlank(username)){
            throw new ServiceException("用户名不能为空");
        }
        if(sysUserMapper.queryByUserName(username)!=null){
            throw new ServiceException("用户名被占用");
        }
        SimpleHash sh = new SimpleHash("MD5","000000",ByteSource.Util.bytes(username),1024);
        user.setPassword(sh.toString());
        user.setCreateUser(ShiroUtil.getUserEntity().getId());
        user.setCreateTime(new Date());
        return sysUserMapper.insertSelective(user);
    }

    @Override
    public SysUser selectById(Long id) {
        if(id == null){
            throw new ServiceException("用户ID不能为空");
        }
        return sysUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(SysUser user) {
        return sysUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Integer queryListCount(Map<String, Object> map) {
        return sysUserMapper.queryListCount(map);
    }
}
