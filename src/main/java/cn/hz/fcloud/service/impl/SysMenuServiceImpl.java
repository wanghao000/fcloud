package cn.hz.fcloud.service.impl;

import cn.hz.fcloud.dao.SysMenuMapper;
import cn.hz.fcloud.entity.SysMenu;
import cn.hz.fcloud.service.SysMenuService;
import cn.hz.fcloud.service.SysUserService;
import cn.hz.fcloud.utils.MenuType;
import cn.hz.fcloud.utils.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysUserService sysUserService;
    @Override
    public List<SysMenu> queryList(Map<String, Object> map) {
        return sysMenuMapper.queryList(map);
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<SysMenu> menuList = sysMenuMapper.queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        //超级管理员不是拥有全部菜单，而是数据库指定的菜单
        /*if(userId == 1){
            return getAllMenuList(null);
        }*/

        //用户菜单列表
        List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }
    /**
     * 获取所有菜单列表
     */
    private List<SysMenu> getAllMenuList(List<Long> menuIdList){
        //查询根菜单列表
        List<SysMenu> menuList = queryListParentId(0L, menuIdList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }
    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<>();

        for(SysMenu entity : menuList){
            if(entity.getType() == MenuType.CATALOG.getValue()){//目录
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    @Override
    public int queryTotal() {
        return sysMenuMapper.queryTotal();
    }

    @Override
    public void save(SysMenu menu) {
        verifyForm(menu);
        int row = sysMenuMapper.insertSelective(menu);
        if(row==0){
            throw new ServiceException("保存失败");
        }
    }

    @Override
    public SysMenu queryObject(Long menuId) {
        return sysMenuMapper.queryObject(menuId);
    }

    @Override
    public void update(SysMenu sysMenu) {
        verifyForm(sysMenu);
        int row = sysMenuMapper.updateByPrimaryKeySelective(sysMenu);
        if(row==0){
            throw new ServiceException("更新失败");
        }
    }

    @Override
    public void deleteBatch(Long[] menuIds) {
        int row = sysMenuMapper.deleteBatch(menuIds);
        if(row==0){
            throw new ServiceException("删除失败");
        }
        if(row!=menuIds.length){
            throw new ServiceException("部分菜单无法删除");
        }
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu){
        if(StringUtils.isBlank(menu.getName())){
            throw new ServiceException("菜单名称不能为空");
        }

        if(menu.getParentId() == null){
            throw new ServiceException("上级菜单不能为空");
        }

        //菜单
        if(menu.getType() == MenuType.MENU.getValue()){
            if(StringUtils.isBlank(menu.getUrl())){
                throw new ServiceException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuType.CATALOG.getValue();
        if(menu.getParentId() != 0){
            SysMenu parentMenu = queryObject(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if(menu.getType() == MenuType.CATALOG.getValue() ||
                menu.getType() == MenuType.MENU.getValue()){
            if(parentType != MenuType.CATALOG.getValue()){
                throw new ServiceException("上级菜单只能为目录类型");
            }
            return ;
        }

        //按钮
        if(menu.getType() == MenuType.BUTTON.getValue()){
            if(parentType != MenuType.MENU.getValue()){
                throw new ServiceException("上级菜单只能为菜单类型");
            }
            return ;
        }
    }
}
